package br.com.events.msauth.clean.process.person.change_password.validations;

import br.com.events.msauth.clean.domain.dto.person.change_passoword.ChangePasswordDTO;
import br.com.events.msauth.clean.process.BaseProcessCaller;

/**
 * This interface dictates which object needs to be validated, {@link ChangePasswordDTO} in this case
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePasswordValidationCaller extends BaseProcessCaller<ChangePasswordDTO, Void> {

    default void validate(ChangePasswordDTO toValidate){
        this.callProcesses(toValidate);
    }
}
