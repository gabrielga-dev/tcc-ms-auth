package br.com.events.msauth.application.validation.person.create.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

public class PersonCreationPasswordEqualityException extends BadRequestException {

    public PersonCreationPasswordEqualityException() {
        super(
            "Erro nas senhas!",
            "Por favor, insira a mesma senha nos respectivos campos."
        );
    }
}
