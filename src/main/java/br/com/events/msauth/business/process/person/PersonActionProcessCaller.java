package br.com.events.msauth.business.process.person;

import br.com.events.msauth.business.process.BaseProcessCaller;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.io.process.ProcessActionType;
import br.com.events.msauth.domain.io.process.ProcessDTO;

public interface PersonActionProcessCaller extends BaseProcessCaller<ProcessDTO<ProcessActionType, Person>, Void> {
}
