package br.com.events.msauth.application.useCase.person;

import br.com.events.msauth.application.service.AuthenticationService;
import br.com.events.msauth.application.useCase.exception.person.PersonIsIsNotTheServiceOwnerException;
import br.com.events.msauth.domain.entity.pk.ServicePk;
import br.com.events.msauth.domain.repository.ServiceRepository;
import br.com.events.msauth.infrastructure.useCase.person.CheckIfPersonIsServiceOwnerUseCase;
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
