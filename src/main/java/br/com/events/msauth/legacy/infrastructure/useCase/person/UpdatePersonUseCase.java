package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.legacy.domain.form.person.update.in.UpdatePersonForm;
import br.com.events.msauth.legacy.domain.form.person.update.in.UpdatePersonUseCaseForm;
import br.com.events.msauth.legacy.domain.form.person.update.out.UpdatePersonResult;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link UpdatePersonForm} to update a person on this use case.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdatePersonUseCase extends UseCaseBase<UpdatePersonUseCaseForm, UpdatePersonResult> {

}
