package br.com.events.msauth.application.useCase.exception.person;

import br.com.events.msauth.infrastructure.exception.badRequest.BadRequestException;

/**
 * This is thrown when a person is not the service's owner
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonIsIsNotTheServiceOwnerException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public PersonIsIsNotTheServiceOwnerException() {
        super(
            "Pessoa não é dona do serviço!",
            "A pessoa em questão não é responsável pelo serviço almejado!"
        );
    }
}
