package br.com.events.msauth.clean.process.person.change_password.validations.validations;

import br.com.events.msauth.clean.domain.dto.person.change_passoword.ChangePasswordDTO;
import br.com.events.msauth.clean.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.clean.process.email_validation.find_by_uuid._use_case.interfaces.FindEmailValidationByUuidUseCase;
import br.com.events.msauth.clean.process.person.change_password.validations.ChangePasswordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangePasswordEmailValidationExistsValidationImpl implements ChangePasswordValidation {

    private final FindEmailValidationByUuidUseCase findEmailValidationByUuidUseCase;

    @Override
    public Void process(ChangePasswordDTO toProcess) {
        var emailValidation = findEmailValidationByUuidUseCase.execute(toProcess.getEmailValidationUuid());
        if (emailValidation.getValidated()){
            throw new EmailValidationNotFoundException();
        }
        return null;
    }
}
