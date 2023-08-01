package br.com.events.msauth.clean.domain.exception._process.authentication;

import br.com.events.msauth.clean.domain.exception.badRequest.BadRequestException;

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
