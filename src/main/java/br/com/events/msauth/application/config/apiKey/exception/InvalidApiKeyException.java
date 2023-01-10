package br.com.events.msauth.application.config.apiKey.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

public class InvalidApiKeyException extends BadRequestException {

    public InvalidApiKeyException() {
        super(
            "Consumidor inválido!",
            "O consumidor do serviço é inválido. Utilize um válido e tente novamente!"
        );
    }
}
