package br.com.events.msauth.infrastructure.exception;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControlExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleException(BusinessException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        var errorMessages = ex.getAllErrors().stream()
            .map(
                error -> String.format(Objects.requireNonNull(error.getDefaultMessage()))
            ).collect(
                Collectors.joining("\n")
            );
        var toReturn = BusinessException.BusinessExceptionBody.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message("Campo(s) inválido(s)!")
            .description(errorMessages.split("\n")[0])
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).body(toReturn);
    }

}