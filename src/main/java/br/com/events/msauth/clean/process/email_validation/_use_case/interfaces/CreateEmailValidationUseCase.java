package br.com.events.msauth.clean.process.email_validation._use_case.interfaces;

import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.domain.entity.Person;

/**
 * This interface dictates that is needed a {@link Person}'s uuid and a {@link EmailValidation} object
 * to save it into the database
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEmailValidationUseCase {

    EmailValidation execute(String personUuid, EmailValidation emailValidation);
}
