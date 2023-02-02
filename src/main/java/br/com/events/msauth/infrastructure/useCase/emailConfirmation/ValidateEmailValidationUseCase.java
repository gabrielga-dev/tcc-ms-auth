package br.com.events.msauth.infrastructure.useCase.emailConfirmation;

import br.com.events.msauth.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to validate a Email validation
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ValidateEmailValidationUseCase extends UseCaseBase<ValidateEmailValidationUseCaseForm, Void> {

}
