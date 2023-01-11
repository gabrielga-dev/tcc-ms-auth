package br.com.events.msauth.infrastructure.validation.person.create;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.infrastructure.validation.BaseValidation;

/**
 * This interface dictates which object needs to be validated, {@link CreatePersonForm} in this case
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface PersonCreationValidation extends BaseValidation<CreatePersonForm> {

}
