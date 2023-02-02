package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates that is needed only the person's uuid to see if the person account is valid
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface SetValidStatusOnPersonByEmailValidationsUseCase extends UseCaseBase<String, Void> {

}
