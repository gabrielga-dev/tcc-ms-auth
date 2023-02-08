package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationUseCaseResult;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates which methods are needed to return the information of the authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface GetAuthenticatedPersonInformationUseCase extends
    UseCaseBase<Void, GetAuthenticatedPersonInformationUseCaseResult> {

}
