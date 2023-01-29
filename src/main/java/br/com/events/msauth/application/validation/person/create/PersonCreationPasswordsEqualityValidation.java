package br.com.events.msauth.application.validation.person.create;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.validation.person.create.exception.PersonCreationPasswordEqualityException;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidation;

/**
 * This class implements the {@link PersonCreationValidation} and validate if the incoming passwords are identical If
 * they aren't, then throws a {@link PersonCreationPasswordEqualityException}.
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
public class PersonCreationPasswordsEqualityValidation implements PersonCreationValidation {

    @Override
    public void validate(final CreatePersonUseCaseForm toValidate) {
        if (!toValidate.getPassword().equals(toValidate.getPasswordRepeated())) {
            throw new PersonCreationPasswordEqualityException();
        }
    }
}
