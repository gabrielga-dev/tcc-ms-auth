package br.com.events.msauth.application.useCase.emailValidation.emailChange;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenEmailException;
import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.mapper.kafkaMessage.SendEmailChangeEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.emailChange.CreateEmailChangeEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.kafkaMessage.SendEmailChangeRequestEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the {@link CreateEmailChangeEmailValidationUseCase} interface and creates a new email
 * validation to change a person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateEmailChangeEmailValidationUseCaseImpl implements CreateEmailChangeEmailValidationUseCase {

    private final PersonRepository personRepository;
    private final EmailValidationRepository emailValidationRepository;
    private final SendEmailChangeRequestEmailValidationKafkaMessageUseCase sendEmailChangeRequestEmailValidationKafkaMessageUseCase;

    @Override
    public Void execute(final String personUuid) {

        var person = personRepository.findByUuidAndActiveTrue(personUuid)
            .orElseThrow(
                NoPersonFoundByGivenEmailException::new
            );

        var emailValidation = new EmailValidation();
        emailValidation.setPerson(person);
        emailValidation.setCreationDate(LocalDateTime.now());
        emailValidation.setType(EmailValidationType.EMAIL_CHANGE);

        var saved = emailValidationRepository.save(emailValidation);

        var passwordChangeKafkaMessage = SendEmailChangeEmailValidationKafkaMessageUseCaseMapper.convertToForm(
            person, saved.getUuid()
        );
        sendEmailChangeRequestEmailValidationKafkaMessageUseCase.execute(passwordChangeKafkaMessage);

        return null;
    }
}
