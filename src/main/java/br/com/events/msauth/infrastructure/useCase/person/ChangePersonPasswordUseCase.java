package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to change a person's password
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePersonPasswordUseCase extends UseCaseBase<ChangePersonPasswordUseCaseForm, Void> {

}
