package br.com.events.msauth.business.use_case.service;

import br.com.events.msauth.adapters.repository.ServiceRepository;
import br.com.events.msauth.domain.entity.Service;
import br.com.events.msauth.domain.entity.pk.ServicePk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindServiceUseCase {

    private final ServiceRepository serviceRepository;

    public Optional<Service> execute(String serviceUuid, String personUuid) {
        var pk = new ServicePk(personUuid, serviceUuid);
        return this.execute(pk);
    }

    public Optional<Service> execute(ServicePk pk) {
        return serviceRepository.findById(pk);
    }
}
