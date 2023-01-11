package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonResult;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link CreatePersonForm} to create a new person on this use case.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreatePersonUseCase extends UseCaseBase<CreatePersonForm, CreatePersonResult> {

}
