package br.com.events.msauth.legacy.domain.form.person.addServiceToPerson.in;

import br.com.events.msauth.clean.domain.entity.type.ServiceType;
import br.com.events.msauth.legacy.infrastructure.useCase.person.AddServiceToPersonUseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is received by {@link AddServiceToPersonUseCase} to add a service to the authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class AddServiceToPersonUseCaseForm {

    private String serviceUuid;
    private ServiceType serviceType;
}
