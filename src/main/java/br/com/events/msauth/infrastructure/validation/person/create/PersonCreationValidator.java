package br.com.events.msauth.infrastructure.validation.person.create;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.infrastructure.validation.BaseValidator;

/**
 * This interface dictates which object needs to be validated, {@link CreatePersonUseCaseForm} in this case
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface PersonCreationValidator extends BaseValidator<CreatePersonUseCaseForm> {

}
