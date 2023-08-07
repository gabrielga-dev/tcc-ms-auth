package br.com.events.msauth.process.person.create.validations;

import br.com.events.msauth.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.process.BaseProcessCaller;

public interface CreatePersonValidationCaller extends BaseProcessCaller<CreatePersonForm, Void> {

    default void validate(CreatePersonForm toValidate){
        this.callProcesses(toValidate);
    }
}
