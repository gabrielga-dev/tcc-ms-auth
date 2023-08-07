package br.com.events.msauth.clean.process.email_validation.create._use_case;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.clean.domain.exception._process.person.NoPersonFoundByGivenEmailException;
import br.com.events.msauth.clean.domain.type.EmailRequestType;
import br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces.CreateEmailValidationUseCase;
import br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces.CreatePasswordChangeEmailValidationUseCase;
import br.com.events.msauth.clean.process.kafka._use_case.interfaces.DispatchEmailValidationKafkaMessageUseCase;
import br.com.events.msauth.clean.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * This class creates a new email validation for password change
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreatePasswordChangeEmailValidationUseCaseImpl implements CreatePasswordChangeEmailValidationUseCase {

    private final PersonRepository personRepository;
    private final CreateEmailValidationUseCase createEmailValidationUseCase;
    private final DispatchEmailValidationKafkaMessageUseCase dispatchEmailValidationKafkaMessageUseCase;

    @Override
    public void execute(final String personEmail) {
        var person = personRepository.findByEmailAndActiveTrue(personEmail)
            .orElseThrow(
                NoPersonFoundByGivenEmailException::new
            );

        var savedEmailValidation = this.saveEmailValidation(person.getUuid());

        this.sendEmailValidation(person, savedEmailValidation.getUuid());
    }

    private EmailValidation saveEmailValidation(String personUuid) {
        var toSave  = new EmailValidation();
        toSave.setType(EmailValidationType.PASSWORD_CHANGE);
        toSave.setCreationDate(LocalDateTime.now());

        return createEmailValidationUseCase.execute(personUuid, toSave);
    }

    private void sendEmailValidation(Person person, String emailValidationUuid) {
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("emailValidationUuid", emailValidationUuid);

        var personCreationKafkaMessage = RawEmailRequest
                .builder()
                .keyAndValues(keyAndValues)
                .type(EmailRequestType.PASSWORD_CHANGE_EMAIL_VALIDATION)
                .build();

        dispatchEmailValidationKafkaMessageUseCase.execute(personCreationKafkaMessage);
    }
}
