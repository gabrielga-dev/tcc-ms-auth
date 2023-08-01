package br.com.events.msauth.legacy.application.useCase.exception.person;

import br.com.events.msauth.clean.domain.exception.badRequest.BadRequestException;

/**
 * This is thrown when someone tries to generate a JWT token with invalid credentials
 *
 * @author Gabriel Guimarães de Almeida
 */
public class InvalidCredentialsToGenerateJwtTokenException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public InvalidCredentialsToGenerateJwtTokenException() {
        super(
            "Credenciais inválidas!",
            "Não foi possivel gerar o token pois as credenciais estão inváliadas ou não pertencem a nenhum usuário!"
        );
    }
}
