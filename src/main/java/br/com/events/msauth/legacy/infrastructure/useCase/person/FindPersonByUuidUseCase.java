package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.legacy.domain.form.person.findByUuid.out.FindPersonByUuidUseCaseResult;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link CreatePersonForm} to create a new person on this use case.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface FindPersonByUuidUseCase extends UseCaseBase<String, FindPersonByUuidUseCaseResult> {

}
