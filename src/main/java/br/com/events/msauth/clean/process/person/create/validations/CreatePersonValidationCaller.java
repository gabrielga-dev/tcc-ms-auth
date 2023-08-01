package br.com.events.msauth.clean.process.person.create.validations;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.clean.process.BaseProcessCaller;

public interface CreatePersonValidationCaller extends BaseProcessCaller<CreatePersonForm, Void> {

    default void validate(CreatePersonForm toValidate){
        this.callProcesses(toValidate);
    }
}
