package br.com.events.msauth.application.validation.person.create.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

/**
 * This is thrown when the incoming passwords, at creating person process, aren't identical
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonCreationPasswordEqualityException extends BadRequestException {

    public PersonCreationPasswordEqualityException() {
        super(
            "Erro nas senhas!",
            "Por favor, insira a mesma senha nos respectivos campos."
        );
    }
}
