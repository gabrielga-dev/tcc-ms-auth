package br.com.events.msauth.clean.process.person.change_password.validations.validations;

import br.com.events.msauth.clean.domain.dto.person.change_passoword.ChangePasswordDTO;
import br.com.events.msauth.clean.process.person.change_password.validations.ChangePasswordValidation;
import br.com.events.msauth.clean.domain.exception._process.person.change_password.ChangePasswordPasswordEqualityException;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordPasswordsEqualityValidationImpl implements ChangePasswordValidation {

    @Override
    public Void process(ChangePasswordDTO toProcess) {
        if (!toProcess.getPassword().equals(toProcess.getPasswordRepeated())) {
            throw new ChangePasswordPasswordEqualityException();
        }
        return null;
    }
}
