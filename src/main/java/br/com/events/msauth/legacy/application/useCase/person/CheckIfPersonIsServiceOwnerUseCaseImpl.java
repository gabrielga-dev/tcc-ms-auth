package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.clean.process.authentication.service.AuthenticationService;
import br.com.events.msauth.legacy.application.useCase.exception.person.PersonIsIsNotTheServiceOwnerException;
import br.com.events.msauth.clean.domain.entity.pk.ServicePk;
import br.com.events.msauth.legacy.domain.repository.ServiceRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.person.CheckIfPersonIsServiceOwnerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements {@link CheckIfPersonIsServiceOwnerUseCase} interface and checks if the authenticated person is
 * the owner of the given service
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CheckIfPersonIsServiceOwnerUseCaseImpl implements CheckIfPersonIsServiceOwnerUseCase {

    private final AuthenticationService authenticationService;
    private final ServiceRepository serviceRepository;

    @Override
    public Void execute(final String param) {

        var personUuid = authenticationService.getAuthenticatedPerson().getUuid();

        var pk = ServicePk
            .builder()
            .serviceUuid(param)
            .personUuid(personUuid)
            .build();

        serviceRepository.findById(pk)
            .orElseThrow(PersonIsIsNotTheServiceOwnerException::new);

        return null;
    }
}
