package br.com.events.msauth.infrastructure.exception.badRequest;

import org.springframework.http.HttpStatus;

import br.com.events.msauth.infrastructure.exception.BusinessException;

public class BadRequestException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(
        final String message, final String description
    ) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message, description);
    }
}
