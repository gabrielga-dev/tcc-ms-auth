package br.com.events.msauth.infrastructure.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class BusinessExceptionBody {

    private final HttpStatus httpStatusCode;

    private final Integer code;

    private final String message;

    private final String description;
}
