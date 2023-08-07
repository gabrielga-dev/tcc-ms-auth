package br.com.events.msauth.process.person.change_password.validations.validations;

import br.com.events.msauth.domain.dto.person.change_passoword.ValidateChangePasswordDTO;
import br.com.events.msauth.process.person.change_password.validations.ChangePasswordValidation;
import br.com.events.msauth.domain.exception._process.person.change_password.ChangePasswordPasswordEqualityException;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordPasswordsEqualityValidationImpl implements ChangePasswordValidation {

    @Override
    public Void process(ValidateChangePasswordDTO toProcess) {
        if (!toProcess.getPassword().equals(toProcess.getPasswordRepeated())) {
            throw new ChangePasswordPasswordEqualityException();
        }
        return null;
    }
}
