package br.com.events.msauth.legacy.infrastructure.validation;

/**
 * This interface dictates which methods are needed to implement when a validation is needed
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseValidation<T> {

    void validate(T toValidate);
}
