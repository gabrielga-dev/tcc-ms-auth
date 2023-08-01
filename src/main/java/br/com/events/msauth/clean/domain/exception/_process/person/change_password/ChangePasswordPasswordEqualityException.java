package br.com.events.msauth.clean.domain.exception._process.person.change_password;

import br.com.events.msauth.clean.domain.exception.badRequest.BadRequestException;

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
