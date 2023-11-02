package br.com.events.msauth.business.exception.person;

import br.com.events.msauth.adapters.exception.controller.badRequest.BadRequestException;

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
