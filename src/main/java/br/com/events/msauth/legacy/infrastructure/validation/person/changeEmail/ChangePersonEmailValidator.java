package br.com.events.msauth.legacy.infrastructure.validation.person.changeEmail;

import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangeEmailRequestUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.validation.BaseValidator;

/**
 * This interface dictates which methods are needed to call the validations to a new person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePersonEmailValidator extends BaseValidator<ChangeEmailRequestUseCaseForm> {

}
