package br.com.events.msauth.infrastructure.validation;

public interface BaseValidator <T>{

    void validate(T toValidate);
}
