package br.com.events.msauth.infrastructure.validation;

/**
 * This interface dictates which methods are needed to implement when a validator is needed
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseValidator<T> {

    void validate(T toValidate);
}
