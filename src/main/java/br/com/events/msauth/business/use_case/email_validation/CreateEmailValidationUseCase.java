package br.com.events.msauth.business.use_case.email_validation;

import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEmailValidationUseCase {

    private final SaveEmailValidationUseCase saveEmailValidationUseCase;

    public EmailValidation fromPersonCreation(Person createdPerson) {
        var toSave = new EmailValidation(createdPerson, EmailValidationType.PERSON_CREATION);
        return saveEmailValidationUseCase.execute(toSave);
    }

    public EmailValidation fromPasswordChange(Person passwordOwner) {
        var toSave = new EmailValidation(passwordOwner, EmailValidationType.PASSWORD_CHANGE);
        return saveEmailValidationUseCase.execute(toSave);
    }

    public EmailValidation fromEmailChange(Person emailOwner) {
        var toSave = new EmailValidation(emailOwner, EmailValidationType.EMAIL_CHANGE);
        return saveEmailValidationUseCase.execute(toSave);
    }
}
