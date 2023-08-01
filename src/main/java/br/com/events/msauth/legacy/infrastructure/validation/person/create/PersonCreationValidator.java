package br.com.events.msauth.legacy.infrastructure.validation.person.create;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.legacy.infrastructure.validation.BaseValidator;

/**
 * This interface dictates which object needs to be validated, {@link CreatePersonForm} in this case
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface PersonCreationValidator extends BaseValidator<CreatePersonForm> {

}
