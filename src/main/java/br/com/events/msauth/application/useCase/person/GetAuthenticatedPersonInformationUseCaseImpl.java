package br.com.events.msauth.application.useCase.person;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.service.AuthenticationService;
import br.com.events.msauth.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationUseCaseResult;
import br.com.events.msauth.domain.mapper.person.GetAuthenticatedPersonInformationUseCaseMapper;
import br.com.events.msauth.infrastructure.useCase.person.GetAuthenticatedPersonInformationUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class implements {@link GetAuthenticatedPersonInformationUseCase} interface and returns the authenticated
 * person's information
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class GetAuthenticatedPersonInformationUseCaseImpl implements GetAuthenticatedPersonInformationUseCase {

    private final AuthenticationService authenticationService;

    @Override
    public GetAuthenticatedPersonInformationUseCaseResult execute(final Void param) {
        var authenticatedPerson = authenticationService.getAuthenticatedPerson();

        return GetAuthenticatedPersonInformationUseCaseMapper.convertToResult(authenticatedPerson);
    }
}
