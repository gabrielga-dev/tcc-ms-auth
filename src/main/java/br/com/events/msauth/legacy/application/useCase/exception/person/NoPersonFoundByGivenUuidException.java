package br.com.events.msauth.legacy.application.useCase.exception.person;

import br.com.events.msauth.clean.domain.exception.badRequest.BadRequestException;

/**
 * This is thrown when a person with a given uuid is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NoPersonFoundByGivenUuidException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public NoPersonFoundByGivenUuidException() {
        super(
            "Pessoa não encontrada!",
            "Não foi possivel encontrar uma pessoa com o identificador informado!"
        );
    }
}
