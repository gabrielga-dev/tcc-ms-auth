package br.com.events.msauth.legacy.application.validation.person.changePassword;

import br.com.events.msauth.legacy.application.validation.person.changePassword.exception.ChangePasswordPasswordEqualityException;
import br.com.events.msauth.clean.domain.exception._process.person.create.PersonCreationPasswordEqualityException;
import br.com.events.msauth.legacy.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.validation.person.changePassword.ChangePersonPasswordValidation;
import org.springframework.stereotype.Component;

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
