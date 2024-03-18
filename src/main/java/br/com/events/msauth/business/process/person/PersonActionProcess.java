package br.com.events.msauth.business.process.person;

import br.com.events.msauth.business.process.BaseProcess;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.io.process.ProcessActionType;
import br.com.events.msauth.domain.io.process.ProcessDTO;

public interface PersonActionProcess extends BaseProcess<ProcessDTO<ProcessActionType, Person>, Void> {
}
