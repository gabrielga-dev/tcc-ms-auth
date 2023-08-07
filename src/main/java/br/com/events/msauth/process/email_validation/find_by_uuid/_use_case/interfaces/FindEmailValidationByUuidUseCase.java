package br.com.events.msauth.process.email_validation.find_by_uuid._use_case.interfaces;

import br.com.events.msauth.domain.entity.EmailValidation;

public interface FindEmailValidationByUuidUseCase {

    EmailValidation execute(String emailValidationUuid);
}
