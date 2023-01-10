package br.com.events.msauth.application.validation.person.create;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.validation.person.create.exception.PersonCreationPasswordEqualityException;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidation;

@Component
public class PersonCreationPasswordsEqualityValidation implements PersonCreationValidation {

    @Override
    public void validate(final CreatePersonForm toValidate) {
        if (!toValidate.getPassword().equals(toValidate.getPasswordRepeated())){
            throw new PersonCreationPasswordEqualityException();
        }
    }
}
