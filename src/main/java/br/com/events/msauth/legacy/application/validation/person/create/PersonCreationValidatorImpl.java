package br.com.events.msauth.legacy.application.validation.person.create;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.legacy.infrastructure.validation.person.create.PersonCreationValidation;
import br.com.events.msauth.legacy.infrastructure.validation.person.create.PersonCreationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class implements {@link PersonCreationValidator} and applies all needed validations for
 * {@link CreatePersonForm} incoming object
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PersonCreationValidatorImpl implements PersonCreationValidator {

    private final List<PersonCreationValidation> validations;

    @Override
    public void validate(final CreatePersonForm toValidate) {
        validations.forEach(
            validation -> validation.validate(toValidate)
        );
    }
}
