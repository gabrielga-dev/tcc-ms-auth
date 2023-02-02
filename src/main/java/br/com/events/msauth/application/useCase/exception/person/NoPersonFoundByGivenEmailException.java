package br.com.events.msauth.application.useCase.exception.person;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

/**
 * This is thrown when a person with a given email is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NoPersonFoundByGivenEmailException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public NoPersonFoundByGivenEmailException() {
        super(
            "Pessoa não encontrada!",
            "Não foi possivel encontrar uma pessoa com o email informado! Lembre-se que é necessário confirmar seu "
                + "email antes de trocar a senha!"
        );
    }
}
