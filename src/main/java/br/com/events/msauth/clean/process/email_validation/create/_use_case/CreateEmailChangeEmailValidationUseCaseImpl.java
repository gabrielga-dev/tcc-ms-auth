package br.com.events.msauth.clean.process.email_validation.create._use_case;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.clean.domain.exception._process.person.NoPersonFoundByGivenEmailException;
import br.com.events.msauth.clean.domain.type.EmailRequestType;
import br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces.CreateEmailChangeEmailValidationUseCase;
import br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces.CreateEmailValidationUseCase;
import br.com.events.msauth.clean.process.kafka._use_case.interfaces.DispatchEmailValidationKafkaMessageUseCase;
import br.com.events.msauth.clean.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

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
    private final CreateEmailValidationUseCase createEmailValidationUseCase;
    private final DispatchEmailValidationKafkaMessageUseCase dispatchEmailValidationKafkaMessageUseCase;

    @Override
    public void execute(final String personUuid) {
        var person = personRepository.findByUuidAndActiveTrue(personUuid)
            .orElseThrow(
                NoPersonFoundByGivenEmailException::new
            );

        var saved = createEmailValidation(person.getUuid());

        this.sendEmailValidation(saved, person);
    }

    private EmailValidation createEmailValidation(String personUuid) {
        var emailValidation = new EmailValidation();
        emailValidation.setCreationDate(LocalDateTime.now());
        emailValidation.setType(EmailValidationType.EMAIL_CHANGE);

        return createEmailValidationUseCase.execute(personUuid, emailValidation);
    }

    private void sendEmailValidation(EmailValidation emailValidation, Person person) {
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("emailValidationUuid", emailValidation.getUuid());

        var personCreationKafkaMessage = RawEmailRequest
                .builder()
                .keyAndValues(keyAndValues)
                .type(EmailRequestType.EMAIL_CHANGE_EMAIL_VALIDATION)
                .build();

        dispatchEmailValidationKafkaMessageUseCase.execute(personCreationKafkaMessage);
    }
}
