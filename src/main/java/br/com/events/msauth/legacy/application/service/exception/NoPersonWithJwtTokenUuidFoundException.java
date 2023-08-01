package br.com.events.msauth.legacy.application.service.exception;

import br.com.events.msauth.clean.domain.exception.badRequest.BadRequestException;

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
