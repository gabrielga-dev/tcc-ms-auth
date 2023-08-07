package br.com.events.msauth.clean.process.person.check_if_person_is_owner._use_case.interfaces;

import br.com.events.msauth.clean.domain.entity.type.ServiceType;

/**
 * This interface dictates which class is needed to pass and check if the authenticated person is the owner of the given
 * service
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CheckIfPersonIsServiceOwnerUseCase {

    void execute(ServiceType serviceType, String serviceUuid);
}
