package br.com.events.msauth.application.config.apiKey.exception;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

public class NoApiKeyReceivedException extends BadRequestException {

    public NoApiKeyReceivedException() {
        super(
            "Consumidor nao identificado!",
            "Não pudemos identificar qual é o consumidor do serviço. Identifique-o e tente novamente!"
        );
    }
}
