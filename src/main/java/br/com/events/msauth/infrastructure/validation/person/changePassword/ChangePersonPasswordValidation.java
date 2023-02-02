package br.com.events.msauth.infrastructure.validation.person.changePassword;

import br.com.events.msauth.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.infrastructure.validation.BaseValidation;

/**
 * This interface dictates which object needs to be validated, {@link ChangePersonPasswordUseCaseForm} in this case
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePersonPasswordValidation extends BaseValidation<ChangePersonPasswordUseCaseForm> {

}
