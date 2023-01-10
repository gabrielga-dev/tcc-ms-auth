package br.com.events.msauth.infrastructure.validation;

public interface BaseValidation <T>{

    void validate(T toValidate);
}
