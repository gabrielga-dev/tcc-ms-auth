package br.com.events.msauth.business.use_case.service;

import br.com.events.msauth.adapters.repository.ServiceRepository;
import br.com.events.msauth.domain.entity.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveServiceUseCase {

    private final ServiceRepository serviceRepository;

    public Service execute(Service toSave) {
        return serviceRepository.save(toSave);
    }
}
