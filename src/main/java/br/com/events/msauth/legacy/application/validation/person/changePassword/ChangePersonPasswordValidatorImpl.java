package br.com.events.msauth.legacy.application.validation.person.changePassword;

import br.com.events.msauth.legacy.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.validation.person.changePassword.ChangePersonPasswordValidation;
import br.com.events.msauth.legacy.infrastructure.validation.person.changePassword.ChangePersonPasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
