package br.com.events.msauth.business.service.email_validation;

import br.com.events.msauth.domain.entity.type.EmailValidationType;

public interface EmailValidationService {

    void checkIfEmailValidationExistsAndNotValidated(String emailValidationUuid);

    void validateEmailValidation(EmailValidationType type, String emailValidationUuid);

    void createPasswordChangeRequest(String email);

    void createEmailChangeRequest(String personUuid);
}
