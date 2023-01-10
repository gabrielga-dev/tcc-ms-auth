package br.com.events.msauth.application.validation.person.create.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

public class PersonCreationEmailAvailableException extends BadRequestException {

    public PersonCreationEmailAvailableException() {
        super(
            "Email já em uso!",
            "O email selecionado se encontra em uso! Utilize outro."
        );
    }
}
