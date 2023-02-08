package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.changeEmail.in.ChangeEmailRequestUseCaseForm;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates which methods are needed to change a person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePersonEmailUseCase extends UseCaseBase<ChangeEmailRequestUseCaseForm, Void> {

}
