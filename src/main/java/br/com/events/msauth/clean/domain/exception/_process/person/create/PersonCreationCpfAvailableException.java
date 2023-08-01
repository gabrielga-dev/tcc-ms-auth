package br.com.events.msauth.clean.domain.exception._process.person.create;

import br.com.events.msauth.clean.domain.exception.badRequest.BadRequestException;

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
