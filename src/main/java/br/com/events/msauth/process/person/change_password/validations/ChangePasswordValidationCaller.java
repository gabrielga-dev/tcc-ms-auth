package br.com.events.msauth.process.person.change_password.validations;

import br.com.events.msauth.domain.dto.person.change_passoword.ValidateChangePasswordDTO;
import br.com.events.msauth.process.BaseProcessCaller;

/**
 * This interface dictates which object needs to be validated, {@link ValidateChangePasswordDTO} in this case
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePasswordValidationCaller extends BaseProcessCaller<ValidateChangePasswordDTO, Void> {

    default void validate(ValidateChangePasswordDTO toValidate){
        this.callProcesses(toValidate);
    }
}
