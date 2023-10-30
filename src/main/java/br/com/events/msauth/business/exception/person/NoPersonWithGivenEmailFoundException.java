package br.com.events.msauth.business.exception.person;

import br.com.events.msauth.adapters.exception.controller.badRequest.BadRequestException;

/**
 * This exception is thrown when no person found with the given email
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NoPersonWithGivenEmailFoundException extends BadRequestException {

    public NoPersonWithGivenEmailFoundException() {
        super(
            "Usuário não encontrado!",
            "Não foi encontrado um usuário com o email informado!"
        );
    }
}
