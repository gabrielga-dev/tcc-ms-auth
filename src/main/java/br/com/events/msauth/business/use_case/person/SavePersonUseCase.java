package br.com.events.msauth.business.use_case.person;

import br.com.events.msauth.adapters.repository.PersonRepository;
import br.com.events.msauth.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavePersonUseCase {

    private final PersonRepository personRepository;

    public Person execute(Person person){
        return personRepository.save(person);
    }
}
