package br.com.events.msauth.process.person.change_email.validations.validations;

import br.com.events.msauth.domain.dto.person.change_email.ChangeEmailDTO;
import br.com.events.msauth.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.process.email_validation.find_by_uuid._use_case.interfaces.FindEmailValidationByUuidUseCase;
import br.com.events.msauth.process.person.change_email.validations.ChangeEmailValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class checks if the incoming new email is available
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangeEmailEmailValidationExistsValidationImpl implements ChangeEmailValidation {

    private final FindEmailValidationByUuidUseCase findEmailValidationByUuidUseCase;

    @Override
    public Void process(final ChangeEmailDTO toValidate) {
        var emailValidation = findEmailValidationByUuidUseCase.execute(toValidate.getEmailValidationUuid());

        if (emailValidation.getValidated() || !emailValidation.getPerson().isActive()){
            throw new EmailValidationNotFoundException();
        }
        return null;
    }
}
