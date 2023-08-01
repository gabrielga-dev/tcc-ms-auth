package br.com.events.msauth.legacy.application.useCase.exception.emailValidation;

import br.com.events.msauth.clean.domain.exception.badRequest.BadRequestException;

/**
 * This exception is thrown when an email validation is not found
 *
 * @author Gabruiel Guimarães de Almeida
 */
public class EmailValidationNotFoundException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public EmailValidationNotFoundException() {
        super(
            "Validação não requisitada!",
            "Não foi possivel validar o email pois a requisição de validação não foi encontrada!"
        );
    }
}
