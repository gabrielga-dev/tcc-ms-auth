package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.legacy.domain.form.emailValidation.passwordChangeRequest.in.CreatePasswordChangeRequestForm;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to create a new password change request
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreatePasswordChangeRequestUseCase extends UseCaseBase<CreatePasswordChangeRequestForm, Void> {

}
