package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.legacy.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationUseCaseResult;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates which methods are needed to return the information of the authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface GetAuthenticatedPersonInformationUseCase extends
    UseCaseBase<Void, GetAuthenticatedPersonInformationUseCaseResult> {

}
