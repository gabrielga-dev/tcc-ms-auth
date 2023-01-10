package br.com.events.msauth.application.validation.person.create;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidation;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidator;
import lombok.RequiredArgsConstructor;

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
