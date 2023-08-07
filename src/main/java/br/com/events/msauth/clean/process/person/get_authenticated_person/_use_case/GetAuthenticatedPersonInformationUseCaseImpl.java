package br.com.events.msauth.clean.process.person.get_authenticated_person._use_case;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;
import br.com.events.msauth.clean.process.authentication.service.AuthenticationService;
import br.com.events.msauth.clean.process.person.get_authenticated_person._use_case.interfaces.GetAuthenticatedPersonInformationUseCase;
import br.com.events.msauth.clean.process.person.get_authenticated_person.mapper.GetAuthenticatedPersonInformationMapper;
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
    public GetAuthenticatedPersonInformationResult execute() {
        var authenticatedPerson = authenticationService.getAuthenticatedPerson();

        return GetAuthenticatedPersonInformationMapper.convertToResult(authenticatedPerson);
    }
}
