package br.com.events.msauth.clean.process.email_validation.validate._use_case.interfaces;

import br.com.events.msauth.clean.domain.dto.email_validation.validate.in.ValidateEmailValidationDTO;

/**
 * This interface dictates what is needed to validate a Email validation
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ValidateEmailValidationUseCase {

    void execute(ValidateEmailValidationDTO form);
}
