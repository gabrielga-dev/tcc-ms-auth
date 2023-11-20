package br.com.events.msauth.business.exception.person;

import br.com.events.msauth.adapters.exception.controller.badRequest.BadRequestException;

/**
 * This is thrown when a person with a given uuid is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NoPersonFoundByGivenCpfException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public NoPersonFoundByGivenCpfException() {
        super(
            "Pessoa não encontrada!",
            "Não foi possivel encontrar uma pessoa com o CPF informado!"
        );
    }
}
