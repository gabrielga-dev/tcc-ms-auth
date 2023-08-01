package br.com.events.msauth.clean.process.process.person.create.validation;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.clean.process.process.BaseProcess;

public interface CreatePersonValidation extends BaseProcess<CreatePersonForm, Void> {

    default void validate(CreatePersonForm toValidate){
        this.process(toValidate);
    }
}
