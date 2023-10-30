package br.com.events.msauth.business.use_case.service;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.entity.Service;
import br.com.events.msauth.domain.entity.pk.ServicePk;
import br.com.events.msauth.domain.entity.type.ServiceType;
import org.springframework.stereotype.Component;

@Component
public class BuildServiceUseCase {

    public Service execute(Person person, String serviceUuid, ServiceType serviceType){
        var pk = new ServicePk();
        pk.setPersonUuid(person.getUuid());
        pk.setServiceUuid(serviceUuid);

        var service = new Service();
        service.setPk(pk);
        service.setType(serviceType);
        service.setPerson(person);

        return service;
    }
}
