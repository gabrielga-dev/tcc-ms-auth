package br.com.events.msauth.business.exception.person;

import br.com.events.msauth.adapters.exception.controller.badRequest.BadRequestException;

/**
 * This is thrown when a person is not the service's owner
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonIsNotTheServiceOwnerException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public PersonIsNotTheServiceOwnerException() {
        super(
            "Pessoa não é dona do serviço!",
            "A pessoa em questão não é responsável pelo serviço almejado!"
        );
    }
}
