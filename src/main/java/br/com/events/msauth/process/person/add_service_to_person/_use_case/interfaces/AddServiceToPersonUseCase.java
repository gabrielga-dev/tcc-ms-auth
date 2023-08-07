package br.com.events.msauth.process.person.add_service_to_person._use_case.interfaces;

import br.com.events.msauth.domain.entity.type.ServiceType;

/**
 * This interface dictates which class is needed to add a service to the authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface AddServiceToPersonUseCase {

    void execute(ServiceType serviceType, String serviceUuid);
}
