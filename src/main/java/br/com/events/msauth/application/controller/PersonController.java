package br.com.events.msauth.application.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import br.com.events.msauth.infrastructure.controller.PersonControllerDoc;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.CheckIfEmailValidationExistsUseCase;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.person.CreatePersonUseCase;
import br.com.events.msauth.infrastructure.useCase.person.GeneratePersonTokenUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class shows all person related endpoint on this microservice
 *
 * @author Gabriel Guimarães de Almeida
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/person")
public class PersonController implements PersonControllerDoc {

    private final CreatePersonUseCase createPersonUseCase;
    private final CheckIfEmailValidationExistsUseCase checkIfEmailValidationExistsUseCase;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;
    private final GeneratePersonTokenUseCase generatePersonTokenUseCase;

    /**
     * This endpoint creates a new person on the database with the given data
     *
     * @param form {@link CreatePersonUseCaseForm} form with the data to validate and save on the database
     * @return {@link ResponseEntity}<{@link URI}> response entity with the URI that you can access the created data
     */
    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid CreatePersonUseCaseForm form) {

        var result = createPersonUseCase.execute(form);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping("/validate-email/{validationUuid}")
    public ResponseEntity<Void> checkIfEmailValidationExists(@PathVariable("validationUuid") String emailValidationUuid) {
        checkIfEmailValidationExistsUseCase.execute(emailValidationUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/validate-email/{validationUuid}")
    public ResponseEntity<Void> create(@PathVariable("validationUuid") String emailValidationUuid) {
        validateEmailValidationUseCase.execute(emailValidationUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/token")
    public ResponseEntity<GeneratePersonTokenResult> generateToken(
        @RequestBody @Valid GeneratePersonTokenForm personTokenForm
    ) {
        return ResponseEntity.ok(
            generatePersonTokenUseCase.execute(personTokenForm)
        );
    }


}
