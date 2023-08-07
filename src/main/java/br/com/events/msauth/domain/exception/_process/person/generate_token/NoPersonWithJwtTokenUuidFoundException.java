package br.com.events.msauth.domain.exception._process.person.generate_token;

import br.com.events.msauth.domain.exception.badRequest.BadRequestException;

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
