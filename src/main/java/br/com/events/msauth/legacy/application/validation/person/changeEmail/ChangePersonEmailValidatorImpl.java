package br.com.events.msauth.legacy.application.validation.person.changeEmail;

import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangeEmailRequestUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.validation.person.changeEmail.ChangePersonEmailValidation;
import br.com.events.msauth.legacy.infrastructure.validation.person.changeEmail.ChangePersonEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class calls the needed validations to validate the new person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangePersonEmailValidatorImpl implements ChangePersonEmailValidator {

    private final List<ChangePersonEmailValidation> validations;

    @Override
    public void validate(final ChangeEmailRequestUseCaseForm toValidate) {
        validations.forEach(
            validation -> validation.validate(toValidate)
        );
    }
}
