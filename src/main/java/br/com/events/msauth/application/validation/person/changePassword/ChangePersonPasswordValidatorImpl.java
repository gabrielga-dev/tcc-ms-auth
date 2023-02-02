package br.com.events.msauth.application.validation.person.changePassword;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.events.msauth.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.infrastructure.validation.person.changePassword.ChangePersonPasswordValidation;
import br.com.events.msauth.infrastructure.validation.person.changePassword.ChangePersonPasswordValidator;
import lombok.RequiredArgsConstructor;

/**
 * This class calls all needed validations for {@link ChangePersonPasswordValidator} objects
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangePersonPasswordValidatorImpl implements ChangePersonPasswordValidator {

    private final List<ChangePersonPasswordValidation> validations;

    @Override
    public void validate(final ChangePersonPasswordUseCaseForm toValidate) {
        validations.forEach(
            validation -> validation.validate(toValidate)
        );
    }
}
