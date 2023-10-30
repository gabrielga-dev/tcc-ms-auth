package br.com.events.msauth.business.use_case.person;

import br.com.events.msauth.adapters.repository.PersonRepository;
import br.com.events.msauth.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindPersonUseCase {

    private final PersonRepository personRepository;

    public Optional<Person> byUuid(String uuid){
        return personRepository.findByUuidAndActiveTrue(uuid);
    }

    public Optional<Person> byCpf(String cpf){
        return personRepository.findByCpfAndActiveTrue(cpf);
    }

    public Optional<Person> byEmail(String email){
        return personRepository.findByEmailAndActiveTrue(email);
    }
}
