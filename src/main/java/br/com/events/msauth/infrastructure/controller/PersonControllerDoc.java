package br.com.events.msauth.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Person Controller")
public interface PersonControllerDoc {

    @ApiOperation(value = "Creates a new person")
    ResponseEntity<URI> create(CreatePersonUseCaseForm form);

    @ApiOperation(value = "Validate if the email validation request exists")
    ResponseEntity<Void> checkIfEmailValidationExists(String emailValidationUuid);

    @ApiOperation(value = "Validate the person's email")
    ResponseEntity<Void> create(String emailValidationUuid);

    @ApiOperation(value = "Generate a person's JWT token")
    ResponseEntity<GeneratePersonTokenResult> generateToken(GeneratePersonTokenForm personTokenForm);
}
