package br.com.events.msauth.business.process.person.impl.actions.email_confirmation.impl.caller;

import br.com.events.msauth.business.process.person.impl.actions.email_confirmation.PersonRoleActivationProcess;
import br.com.events.msauth.business.process.person.impl.actions.email_confirmation.PersonRoleActivationProcessCaller;
import br.com.events.msauth.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonRoleActivationProcessCallerImpl implements PersonRoleActivationProcessCaller {

    private final List<PersonRoleActivationProcess> processes;

    @Override
    public Void submitToProcesses(Person person) {
        processes
                .stream()
                .filter(process -> process.isAccepted(person))
                .forEach(process -> process.process(person));
        return null;
    }
}
