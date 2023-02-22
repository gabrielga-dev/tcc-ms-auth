package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.addServiceToPerson.in.AddServiceToPersonUseCaseForm;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates which class is needed to add a service to the authenticated person
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface AddServiceToPersonUseCase extends UseCaseBase<AddServiceToPersonUseCaseForm, Void> {

}
