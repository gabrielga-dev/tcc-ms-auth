package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.legacy.domain.form.person.addServiceToPerson.in.AddServiceToPersonUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates which class is needed to add a service to the authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface AddServiceToPersonUseCase extends UseCaseBase<AddServiceToPersonUseCaseForm, Void> {

}
