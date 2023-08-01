package br.com.events.msauth.clean.process.email_validation.find_by_uuid._use_case.interfaces;

import br.com.events.msauth.clean.domain.entity.EmailValidation;

public interface FindEmailValidationByUuidUseCase {

    EmailValidation execute(String emailValidationUuid);
}
