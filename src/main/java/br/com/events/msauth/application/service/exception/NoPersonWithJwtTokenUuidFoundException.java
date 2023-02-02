package br.com.events.msauth.application.service.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

/**
 * This exception is thrown when a person is not found with the given token uuid
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NoPersonWithJwtTokenUuidFoundException extends BadRequestException {

    public NoPersonWithJwtTokenUuidFoundException() {
        super(
            "Token inválido!",
            "O token não pertence a nenhum dos usuários do sistema!"
        );
    }
}
