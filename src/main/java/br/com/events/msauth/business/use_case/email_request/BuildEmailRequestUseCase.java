package br.com.events.msauth.business.use_case.email_request;

import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.io.kafka_messages.RawEmailRequest;
import br.com.events.msauth.domain.type.EmailRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class BuildEmailRequestUseCase {

    public RawEmailRequest fromPersonCreation(Person person, EmailValidation emailValidation){
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("emailValidationUuid", emailValidation.getUuid());

        return new RawEmailRequest(EmailRequestType.PERSON_CREATION, keyAndValues);
    }

    public RawEmailRequest fromEmailChange(String emailValidationUuid, Person person) {
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("emailValidationUuid", emailValidationUuid);

        return new RawEmailRequest(EmailRequestType.EMAIL_CHANGE_EMAIL_VALIDATION, keyAndValues);
    }

    public RawEmailRequest fromEmailChanged(String emailValidationUuid, Person person) {
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("emailValidationUuid", emailValidationUuid);

        return new RawEmailRequest(EmailRequestType.EMAIL_CHANGED, keyAndValues);
    }

    public RawEmailRequest fromChangePasswordRequest(String emailValidationUuid, Person person) {
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("emailValidationUuid", emailValidationUuid);

        return new RawEmailRequest(EmailRequestType.PASSWORD_CHANGE_EMAIL_VALIDATION, keyAndValues);
    }
}
