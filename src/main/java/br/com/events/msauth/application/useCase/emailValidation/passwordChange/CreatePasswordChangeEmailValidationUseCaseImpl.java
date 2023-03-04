package br.com.events.msauth.application.useCase.emailValidation.passwordChange;

import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenEmailException;
import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.mapper.kafkaMessage.SendPasswordChangeEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.passwordChange.CreatePasswordChangeEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.kafkaMessage.SendPasswordChangeEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * This class creates a new email validation for password change
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreatePasswordChangeEmailValidationUseCaseImpl implements CreatePasswordChangeEmailValidationUseCase {


    private final EmailValidationRepository emailValidationRepository;
    private final PersonRepository personRepository;
    private final SendPasswordChangeEmailValidationKafkaMessageUseCase sendPasswordChangeEmailValidationKafkaMessageUseCase;

    @Override
    public Void execute(final String personEmail) {

        var person = personRepository.findByEmailAndActiveTrue(personEmail)
            .orElseThrow(
                NoPersonFoundByGivenEmailException::new
            );

        var toSave  = new EmailValidation();
        toSave.setPerson(person);
        toSave.setType(EmailValidationType.PASSWORD_CHANGE);
        toSave.setCreationDate(LocalDateTime.now());

        var saved = emailValidationRepository.save(toSave);

        var passwordChangeKafkaMessage = SendPasswordChangeEmailValidationKafkaMessageUseCaseMapper.convertToForm(
            person, saved.getUuid()
        );
        sendPasswordChangeEmailValidationKafkaMessageUseCase.execute(passwordChangeKafkaMessage);

        return null;
    }
}
