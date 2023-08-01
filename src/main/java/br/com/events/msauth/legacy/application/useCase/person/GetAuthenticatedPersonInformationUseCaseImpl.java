package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.legacy.application.service.AuthenticationService;
import br.com.events.msauth.legacy.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationUseCaseResult;
import br.com.events.msauth.legacy.domain.mapper.person.GetAuthenticatedPersonInformationUseCaseMapper;
import br.com.events.msauth.legacy.infrastructure.useCase.person.GetAuthenticatedPersonInformationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
