package br.com.events.msauth.process.person.change_email.validations.caller;

import br.com.events.msauth.domain.dto.person.change_email.ChangeEmailDTO;
import br.com.events.msauth.process.person.change_email.validations.ChangeEmailValidation;
import br.com.events.msauth.process.person.change_email.validations.ChangeEmailValidationCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChangeEmailValidationCallerImpl implements ChangeEmailValidationCaller {

    private final List<ChangeEmailValidation> validations;

    @Override
    public Void callProcesses(ChangeEmailDTO toProcess) {
        validations.forEach(
                validation -> validation.validate(toProcess)
        );
        return null;
    }
}
