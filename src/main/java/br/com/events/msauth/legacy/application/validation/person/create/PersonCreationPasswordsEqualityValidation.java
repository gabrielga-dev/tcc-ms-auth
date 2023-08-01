package br.com.events.msauth.legacy.application.validation.person.create;

import br.com.events.msauth.clean.domain.exception._process.person.create.PersonCreationPasswordEqualityException;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.legacy.infrastructure.validation.person.create.PersonCreationValidation;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link PersonCreationValidation} and validate if the incoming passwords are identical If
 * they aren't, then throws a {@link PersonCreationPasswordEqualityException}.
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
public class PersonCreationPasswordsEqualityValidation implements PersonCreationValidation {

    @Override
    public void validate(final CreatePersonForm toValidate) {
        if (!toValidate.getPassword().equals(toValidate.getPasswordRepeated())) {
            throw new PersonCreationPasswordEqualityException();
        }
    }
}
