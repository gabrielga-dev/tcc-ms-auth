package br.com.events.msauth.infrastructure.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;

public interface PersonControllerDoc {

    ResponseEntity<URI> create(CreatePersonForm form);
}
