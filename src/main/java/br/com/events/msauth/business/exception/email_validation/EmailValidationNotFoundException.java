package br.com.events.msauth.business.exception.email_validation;

import br.com.events.msauth.adapters.exception.controller.badRequest.BadRequestException;

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
