package br.com.events.msauth.business.exception.person;

import br.com.events.msauth.adapters.exception.controller.badRequest.BadRequestException;

/**
 * This is thrown when the incoming CPF, at creating person process, isn't available
 *
 * @author Gabriel Guimarães de Almeida
 */
public class CpfNotAvailableException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public CpfNotAvailableException() {
        super(
            "CPF já em uso!",
            "O CPF selecionado se encontra em uso!"
        );
    }
}
