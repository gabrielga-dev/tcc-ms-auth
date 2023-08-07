package br.com.events.msauth.domain.exception._process.person.change_email;

import br.com.events.msauth.domain.exception.badRequest.BadRequestException;

/**
 * This is thrown when the incoming email, at changing person's email process, isn't available
 *
 * @author Gabriel Guimarães de Almeida
 */
public class ChangePersonEmailEmailNotAvailableException extends BadRequestException {

    public ChangePersonEmailEmailNotAvailableException() {
        super(
            "Email já em uso!",
            "O email selecionado se encontra em uso! Utilize outro."
        );
    }
}
