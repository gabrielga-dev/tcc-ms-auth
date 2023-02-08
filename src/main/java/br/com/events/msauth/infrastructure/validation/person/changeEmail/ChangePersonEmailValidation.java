package br.com.events.msauth.infrastructure.validation.person.changeEmail;

import br.com.events.msauth.domain.form.person.changeEmail.in.ChangeEmailRequestUseCaseForm;
import br.com.events.msauth.infrastructure.validation.BaseValidation;

/**
 * This interface dictates which methods are needed to validate a new person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePersonEmailValidation extends BaseValidation<ChangeEmailRequestUseCaseForm> {

}
