package br.com.events.msauth.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
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
    ResponseEntity<URI> create(CreatePersonForm form);
}
