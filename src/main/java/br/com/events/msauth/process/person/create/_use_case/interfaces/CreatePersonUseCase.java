package br.com.events.msauth.process.person.create._use_case.interfaces;

import br.com.events.msauth.infrastructure.controller.entity.person.create.in.CreatePersonForm;

/**
 * This interface dictates that needs a {@link CreatePersonForm} to create a new person on this use case.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreatePersonUseCase {

    String execute(CreatePersonForm form);
}
