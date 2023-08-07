package br.com.events.msauth.process.person.create.validations;

import br.com.events.msauth.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.process.BaseProcess;

public interface CreatePersonValidation extends BaseProcess<CreatePersonForm, Void> {

    default void validate(CreatePersonForm toValidate){
        this.process(toValidate);
    }
}
