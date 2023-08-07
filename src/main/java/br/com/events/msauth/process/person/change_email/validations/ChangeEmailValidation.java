package br.com.events.msauth.process.person.change_email.validations;

import br.com.events.msauth.domain.dto.person.change_email.ChangeEmailDTO;
import br.com.events.msauth.process.BaseProcess;

public interface ChangeEmailValidation extends BaseProcess<ChangeEmailDTO, Void> {

    default void validate(ChangeEmailDTO toValidate){
        this.process(toValidate);
    }
}
