package br.com.events.msauth.application.useCase.person;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonUseCaseResult;
import br.com.events.msauth.domain.mapper.kafkaMessage.SendPersonCreationEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.domain.mapper.person.CreatePersonUseCaseMapper;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.CreatePersonCreationEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.kafkaMessage.SendPersonCreationEmailValidationKafkaMessageUseCase;
import br.com.events.msauth.infrastructure.useCase.person.CreatePersonUseCase;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidator;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the {@link CreatePersonUseCase} interface and creates a new person at database, create a new
 * email validation and send it through a kafka queue to MS-MAILER send it to the person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreatePersonUseCaseImpl implements CreatePersonUseCase {

    private final PersonRepository repository;
    private final EmailValidationRepository emailValidationRepository;
    private final PersonCreationValidator validator;

    private final CreatePersonCreationEmailValidationUseCase createPersonCreationEmailValidationUseCase;
    private final SendPersonCreationEmailValidationKafkaMessageUseCase sendPersonCreationEmailValidationKafkaMessageUseCase;

    @Override
    public CreatePersonUseCaseResult execute(final CreatePersonUseCaseForm param) {

        validator.validate(param);

        var toSave = CreatePersonUseCaseMapper.convertToEntity(param);
        toSave.setCreationDate(LocalDateTime.now());

        var saved = repository.save(toSave);

        createPersonCreationEmailValidationUseCase.execute(saved.getUuid());

        var personCreationKafkaMessage = SendPersonCreationEmailValidationKafkaMessageUseCaseMapper.convertToForm(
            saved,
            emailValidationRepository.findByPersonUuid(saved.getUuid()).get(0).getUuid()
        );

        sendPersonCreationEmailValidationKafkaMessageUseCase.execute(personCreationKafkaMessage);

        return CreatePersonUseCaseMapper.convertToResult(saved);
    }
}
