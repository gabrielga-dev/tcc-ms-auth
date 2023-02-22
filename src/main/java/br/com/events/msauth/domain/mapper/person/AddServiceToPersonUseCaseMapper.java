package br.com.events.msauth.domain.mapper.person;


import br.com.events.msauth.domain.entity.type.ServiceType;
import br.com.events.msauth.domain.form.person.addServiceToPerson.in.AddServiceToPersonUseCaseForm;
import br.com.events.msauth.infrastructure.useCase.person.AddServiceToPersonUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at {@link AddServiceToPersonUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddServiceToPersonUseCaseMapper {

    public static AddServiceToPersonUseCaseForm toUseCaseForm(String serviceUuid, ServiceType serviceType){
        return AddServiceToPersonUseCaseForm
            .builder()
            .serviceType(serviceType)
            .serviceUuid(serviceUuid)
            .build();
    }
}
