package br.com.events.msauth.process.person.create.validations.validations;

import br.com.events.msauth.domain.exception._process.person.create.PersonCreationPasswordEqualityException;
import br.com.events.msauth.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.process.person.create.validations.CreatePersonValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link CreatePersonValidation} and validate if the incoming passwords are identical If
 * they aren't, then throws a {@link PersonCreationPasswordEqualityException}.
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PersonCreationPasswordsEqualityValidationImpl implements CreatePersonValidation {

    @Override
    public Void process(CreatePersonForm toValidate) {
        if (!toValidate.getPassword().equals(toValidate.getPasswordRepeated())) {
            throw new PersonCreationPasswordEqualityException();
        }
        return null;
    }
}
