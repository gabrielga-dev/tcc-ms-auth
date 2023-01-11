package br.com.events.msauth.application.validation.person.create.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

/**
 * This is thrown when the incoming email, at creating person process, isn't available
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonCreationEmailAvailableException extends BadRequestException {

    public PersonCreationEmailAvailableException() {
        super(
            "Email já em uso!",
            "O email selecionado se encontra em uso! Utilize outro."
        );
    }
}
