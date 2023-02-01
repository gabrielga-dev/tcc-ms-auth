package br.com.events.msauth.application.service.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

public class NoPersonWithJwtTokenUuidFoundException extends BadRequestException {

    public NoPersonWithJwtTokenUuidFoundException() {
        super(
            "Token inválido!",
            "O token não pertence a nenhum dos usuários do sistema!"
        );
    }
}
