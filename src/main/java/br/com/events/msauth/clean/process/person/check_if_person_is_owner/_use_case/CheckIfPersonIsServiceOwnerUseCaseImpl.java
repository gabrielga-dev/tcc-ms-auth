package br.com.events.msauth.clean.process.person.check_if_person_is_owner._use_case;

import br.com.events.msauth.clean.domain.entity.pk.ServicePk;
import br.com.events.msauth.clean.domain.entity.type.ServiceType;
import br.com.events.msauth.clean.process.authentication.service.AuthenticationService;
import br.com.events.msauth.clean.process.person.check_if_person_is_owner._use_case.interfaces.CheckIfPersonIsServiceOwnerUseCase;
import br.com.events.msauth.legacy.application.useCase.exception.person.PersonIsIsNotTheServiceOwnerException;
import br.com.events.msauth.legacy.domain.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
    public void execute(ServiceType serviceType, final String serviceUuid) {

        var personUuid = authenticationService.getAuthenticatedPerson().getUuid();

        var pk = ServicePk
                .builder()
                .serviceUuid(serviceUuid)
                .personUuid(personUuid)
                .build();

        var service = serviceRepository.findById(pk)
                .orElseThrow(PersonIsIsNotTheServiceOwnerException::new);

        if (Objects.equals(service.getType(), serviceType)) {
            throw new PersonIsIsNotTheServiceOwnerException();
        }
    }
}
