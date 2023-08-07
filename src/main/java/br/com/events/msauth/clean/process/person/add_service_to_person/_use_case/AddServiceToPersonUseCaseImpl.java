package br.com.events.msauth.clean.process.person.add_service_to_person._use_case;

import br.com.events.msauth.clean.domain.entity.Service;
import br.com.events.msauth.clean.domain.entity.pk.ServicePk;
import br.com.events.msauth.clean.domain.entity.type.ServiceType;
import br.com.events.msauth.clean.domain.exception._process.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.clean.process.authentication.service.AuthenticationService;
import br.com.events.msauth.clean.process.person.add_service_to_person._use_case.interfaces.AddServiceToPersonUseCase;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.domain.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link AddServiceToPersonUseCase} interface and add the given service to the authenticated
 * person
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class AddServiceToPersonUseCaseImpl implements AddServiceToPersonUseCase {

    private final AuthenticationService authenticationService;
    private final ServiceRepository serviceRepository;
    private final PersonRepository personRepository;

    @Override
    public void execute(ServiceType serviceType, String serviceUuid) {
        var authenticatedPersonUuid = authenticationService.getAuthenticatedPerson().getUuid();

        var pk = new ServicePk();
        pk.setPersonUuid(authenticatedPersonUuid);
        pk.setServiceUuid(serviceUuid);

        var toSave = new Service();
        toSave.setPk(pk);
        toSave.setType(serviceType);
        toSave.setPerson(
            personRepository.findById(authenticatedPersonUuid)
                .orElseThrow(NoPersonFoundByGivenUuidException::new)
        );

        serviceRepository.save(toSave);
    }
}
