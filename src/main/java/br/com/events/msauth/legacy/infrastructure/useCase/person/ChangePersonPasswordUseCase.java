package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.legacy.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to change a person's password
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePersonPasswordUseCase extends UseCaseBase<ChangePersonPasswordUseCaseForm, Void> {

}
