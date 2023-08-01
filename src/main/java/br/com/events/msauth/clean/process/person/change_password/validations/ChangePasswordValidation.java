package br.com.events.msauth.clean.process.person.change_password.validations;

import br.com.events.msauth.clean.domain.dto.person.change_passoword.ValidateChangePasswordDTO;
import br.com.events.msauth.clean.process.BaseProcess;

/**
 * This interface dictates which object needs to be validated, {@link ValidateChangePasswordDTO} in this case
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePasswordValidation extends BaseProcess<ValidateChangePasswordDTO, Void> {

    default void validate(ValidateChangePasswordDTO toValidate){
        this.process(toValidate);
    }
}
