package br.com.events.msauth.domain.form.person.addServiceToPerson.in;

import br.com.events.msauth.domain.entity.type.ServiceType;
import br.com.events.msauth.infrastructure.useCase.person.AddServiceToPersonUseCase;
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
