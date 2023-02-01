package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonUseCaseResult;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link CreatePersonUseCaseForm} to create a new person on this use case.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreatePersonUseCase extends UseCaseBase<CreatePersonUseCaseForm, CreatePersonUseCaseResult> {

}
