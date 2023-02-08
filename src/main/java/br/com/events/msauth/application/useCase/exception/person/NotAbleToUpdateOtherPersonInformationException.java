package br.com.events.msauth.application.useCase.exception.person;

import br.com.events.msauth.infrastructure.exception.badRequest.ForbiddenRequestException;

/**
 * This is thrown when a person tries to update other's person information
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NotAbleToUpdateOtherPersonInformationException extends ForbiddenRequestException {

    public NotAbleToUpdateOtherPersonInformationException() {
        super(
            "Impossível realizar a ação!",
            "Você não pode alterar as informações de outro usuário, apenas as suas!"
        );
    }
}
