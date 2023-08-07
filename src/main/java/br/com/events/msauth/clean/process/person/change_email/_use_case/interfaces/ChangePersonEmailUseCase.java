package br.com.events.msauth.clean.process.person.change_email._use_case.interfaces;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.change_email.in.ChangePersonEmailForm;

/**
 * This interface dictates which methods are needed to change a person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ChangePersonEmailUseCase {

    void execute(String emailValidationUuid, ChangePersonEmailForm form);
}
