package br.com.events.msauth.application.validation.person.changePassword;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.validation.person.changePassword.exception.ChangePasswordPasswordEqualityException;
import br.com.events.msauth.application.validation.person.create.exception.PersonCreationPasswordEqualityException;
import br.com.events.msauth.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.infrastructure.validation.person.changePassword.ChangePersonPasswordValidation;

/**
 * This class implements the {@link ChangePersonPasswordValidation} and validate if the incoming passwords
 * are identical If they aren't, then throws a {@link PersonCreationPasswordEqualityException}.
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
public class ChangePasswordPasswordsEqualityValidation implements ChangePersonPasswordValidation {

    @Override
    public void validate(final ChangePersonPasswordUseCaseForm toValidate) {
        if (!toValidate.getPassword().equals(toValidate.getPasswordRepeated())) {
            throw new ChangePasswordPasswordEqualityException();
        }
    }
}
