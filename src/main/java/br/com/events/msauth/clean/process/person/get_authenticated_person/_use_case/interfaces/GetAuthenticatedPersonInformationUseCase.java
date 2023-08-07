package br.com.events.msauth.clean.process.person.get_authenticated_person._use_case.interfaces;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;

/**
 * This interface dictates which methods are needed to return the information of the authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface GetAuthenticatedPersonInformationUseCase {

    GetAuthenticatedPersonInformationResult execute();
}
