package br.com.events.msauth.process.person.change_password.validations.validations;

import br.com.events.msauth.domain.dto.person.change_passoword.ValidateChangePasswordDTO;
import br.com.events.msauth.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.process.email_validation.find_by_uuid._use_case.interfaces.FindEmailValidationByUuidUseCase;
import br.com.events.msauth.process.person.change_password.validations.ChangePasswordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangePasswordEmailValidationExistsValidationImpl implements ChangePasswordValidation {

    private final FindEmailValidationByUuidUseCase findEmailValidationByUuidUseCase;

    @Override
    public Void process(ValidateChangePasswordDTO toProcess) {
        var emailValidation = findEmailValidationByUuidUseCase.execute(toProcess.getEmailValidationUuid());
        if (emailValidation.getValidated() || !emailValidation.getPerson().isActive()){
            throw new EmailValidationNotFoundException();
        }
        return null;
    }
}
