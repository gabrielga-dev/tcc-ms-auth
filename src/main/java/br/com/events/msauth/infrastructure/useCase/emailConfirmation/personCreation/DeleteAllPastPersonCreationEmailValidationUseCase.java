package br.com.events.msauth.infrastructure.useCase.emailConfirmation.personCreation;

import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link String} person uuid to delete all past person creation email validation.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface DeleteAllPastPersonCreationEmailValidationUseCase extends UseCaseBase<String, Void> {

}
