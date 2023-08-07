package br.com.events.msauth.process.email_validation.create._use_case.interfaces;

import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.Person;

/**
 * This interface dictates that is needed a {@link Person}'s uuid and a {@link EmailValidation} object
 * to save it into the database
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEmailValidationUseCase {

    EmailValidation execute(String personUuid, EmailValidation emailValidation);
}
