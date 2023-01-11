package br.com.events.msauth.application.validation.person.create.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

/**
 * This is thrown when the incoming CPF, at creating person process, isn't available
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonCreationCpfAvailableException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public PersonCreationCpfAvailableException() {
        super(
            "CPF já em uso!",
            "O CPF selecionado se encontra em uso!"
        );
    }
}
