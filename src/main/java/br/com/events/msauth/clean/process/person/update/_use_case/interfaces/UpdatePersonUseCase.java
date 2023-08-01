package br.com.events.msauth.clean.process.person.update._use_case.interfaces;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.update.in.UpdatePersonForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.update.out.UpdatePersonResult;

/**
 * This interface dictates that needs a {@link UpdatePersonForm} to update a person on this use case.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface UpdatePersonUseCase {

    UpdatePersonResult execute(String personUuid, UpdatePersonForm form);
}
