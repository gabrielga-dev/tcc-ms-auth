package br.com.events.msauth.infrastructure.useCase.emailConfirmation.personCreation;

import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link String} person uuid to create a new email validation.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreatePersonCreationEmailValidationUseCase extends UseCaseBase<String, Void> {

}
