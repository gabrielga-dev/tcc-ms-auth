package br.com.events.msauth.business.process.person.impl.process;

import br.com.events.msauth.business.process.person.PersonActionProcess;
import br.com.events.msauth.business.process.person.impl.actions.person_update.PersonUpdateProcessCaller;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.io.process.ProcessActionType;
import br.com.events.msauth.domain.io.process.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonUpdateActionProcessImpl implements PersonActionProcess {

    private final PersonUpdateProcessCaller processCaller;

    @Override
    public boolean isAccepted(ProcessDTO<ProcessActionType, Person> param) {
        return ProcessActionType.PERSON_UPDATE.equals(param.getKey()) ||
                ProcessActionType.EMAIL_CHANGE.equals(param.getKey());
    }

    @Override
    public Void process(ProcessDTO<ProcessActionType, Person> param) {
        processCaller.submitToProcesses(param.getData());
        return null;
    }
}
