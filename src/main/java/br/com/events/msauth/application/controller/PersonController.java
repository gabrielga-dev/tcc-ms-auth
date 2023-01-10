package br.com.events.msauth.application.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.infrastructure.controller.PersonControllerDoc;
import br.com.events.msauth.infrastructure.useCase.person.CreatePersonUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/person")
public class PersonController implements PersonControllerDoc {

    private final CreatePersonUseCase createPersonUseCase;

    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid CreatePersonForm form){

        var result = createPersonUseCase.execute(form);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).build();
    }
}
