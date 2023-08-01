package br.com.events.msauth.clean.process.person.change_password.validations.caller;

import br.com.events.msauth.clean.domain.dto.person.change_passoword.ChangePasswordDTO;
import br.com.events.msauth.clean.process.person.change_password.validations.ChangePasswordValidation;
import br.com.events.msauth.clean.process.person.change_password.validations.ChangePasswordValidationCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChangePasswordValidationCallerImpl implements ChangePasswordValidationCaller {

    private final List<ChangePasswordValidation> validations;

    @Override
    public Void callProcesses(ChangePasswordDTO toProcess) {
        validations.forEach(
                validation -> validation.validate(toProcess)
        );
        return null;
    }
}
