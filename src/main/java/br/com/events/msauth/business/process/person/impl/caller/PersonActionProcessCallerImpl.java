package br.com.events.msauth.business.process.person.impl.caller;

import br.com.events.msauth.business.process.person.PersonActionProcess;
import br.com.events.msauth.business.process.person.PersonActionProcessCaller;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.io.process.ProcessActionType;
import br.com.events.msauth.domain.io.process.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonActionProcessCallerImpl implements PersonActionProcessCaller {

    private final List<PersonActionProcess> processes;

    @Override
    public Void submitToProcesses(ProcessDTO<ProcessActionType, Person> param) {
        processes.stream()
                .filter(process -> process.isAccepted(param))
                .forEach(process -> process.process(param));
        return null;
    }
}
