package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates which class is needed to pass and check if the authenticated person is the owner of the given
 * service
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CheckIfPersonIsServiceOwnerUseCase extends UseCaseBase<String, Void> {

}
