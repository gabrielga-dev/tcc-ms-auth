package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.legacy.application.service.AuthenticationService;
import br.com.events.msauth.legacy.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.clean.domain.entity.Service;
import br.com.events.msauth.clean.domain.entity.pk.ServicePk;
import br.com.events.msauth.legacy.domain.form.person.addServiceToPerson.in.AddServiceToPersonUseCaseForm;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.domain.repository.ServiceRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.person.AddServiceToPersonUseCase;
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
    public Void execute(final AddServiceToPersonUseCaseForm param) {
        var authenticatedPersonUuid = authenticationService.getAuthenticatedPerson().getUuid();

        var pk = new ServicePk();
        pk.setPersonUuid(authenticatedPersonUuid);
        pk.setServiceUuid(param.getServiceUuid());

        var toSave = new Service();
        toSave.setPk(pk);
        toSave.setType(param.getServiceType());
        toSave.setPerson(
            personRepository.findById(authenticatedPersonUuid)
                .orElseThrow(NoPersonFoundByGivenUuidException::new)
        );

        serviceRepository.save(toSave);

        return null;
    }
}
