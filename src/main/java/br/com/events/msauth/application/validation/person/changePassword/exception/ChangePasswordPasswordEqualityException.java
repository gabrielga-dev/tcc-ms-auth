package br.com.events.msauth.application.validation.person.changePassword.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

/**
 * This is thrown when the incoming passwords, at changing password process, aren't identical
 *
 * @author Gabriel Guimarães de Almeida
 */
public class ChangePasswordPasswordEqualityException extends BadRequestException {

    public ChangePasswordPasswordEqualityException() {
        super(
            "Erro nas senhas!",
            "Por favor, insira a mesma senha nos respectivos campos."
        );
    }
}
