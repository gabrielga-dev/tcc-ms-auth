package br.com.events.msauth.business.process.person.impl.actions.person_update.impl.caller;

import br.com.events.msauth.business.process.person.impl.actions.person_update.PersonUpdateProcess;
import br.com.events.msauth.business.process.person.impl.actions.person_update.PersonUpdateProcessCaller;
import br.com.events.msauth.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonUpdateProcessCallerImpl implements PersonUpdateProcessCaller {

    private final List<PersonUpdateProcess> processes;

    @Override
    public Void submitToProcesses(Person person) {
        processes.stream()
                .filter(process -> process.isAccepted(person))
                .findFirst()
                .orElseThrow()
                .process(person);
        return null;
    }
}
