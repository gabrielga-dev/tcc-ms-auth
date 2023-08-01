package br.com.events.msauth.legacy.infrastructure.useCase.emailConfirmation;

import br.com.events.msauth.clean.domain.dto.email_validation.validate.in.ValidateEmailValidationDTO;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to validate a Email validation
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ValidateEmailValidationUseCase extends UseCaseBase<ValidateEmailValidationDTO, Void> {

}
