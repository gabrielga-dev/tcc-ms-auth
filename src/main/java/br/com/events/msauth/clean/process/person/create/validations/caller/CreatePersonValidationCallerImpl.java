package br.com.events.msauth.clean.process.person.create.validations.caller;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.clean.process.person.create.validations.CreatePersonValidation;
import br.com.events.msauth.clean.process.person.create.validations.CreatePersonValidationCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreatePersonValidationCallerImpl implements CreatePersonValidationCaller {

    private final List<CreatePersonValidation> validations;

    @Override
    public Void callProcesses(CreatePersonForm toValidate) {
        validations.forEach(
                validation -> validation.validate(toValidate)
        );
        return null;
    }
}
