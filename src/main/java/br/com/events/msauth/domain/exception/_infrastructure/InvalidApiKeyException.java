package br.com.events.msauth.domain.exception._infrastructure;

import br.com.events.msauth.domain.exception.badRequest.BadRequestException;

/**
 * This exception is called when a request with an invalid key is received
 *
 * @author Gabriel Guimarães de Almeida
 */
public class InvalidApiKeyException extends BadRequestException {

    public InvalidApiKeyException() {
        super(
            "Consumidor inválido!",
            "O consumidor do serviço é inválido. Utilize um válido e tente novamente!"
        );
    }
}
