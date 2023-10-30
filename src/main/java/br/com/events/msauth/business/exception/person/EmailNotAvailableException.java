package br.com.events.msauth.business.exception.person;

import br.com.events.msauth.adapters.exception.controller.badRequest.BadRequestException;

/**
 * This is thrown when the incoming email, at creating person process, isn't available
 *
 * @author Gabriel Guimarães de Almeida
 */
public class EmailNotAvailableException extends BadRequestException {

    public EmailNotAvailableException() {
        super(
            "Email já em uso!",
            "O email selecionado se encontra em uso! Utilize outro."
        );
    }
}
