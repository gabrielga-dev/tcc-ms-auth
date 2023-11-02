package br.com.events.msauth.adapters.controller.v1.person;

import br.com.events.msauth.business.service.person.PersonService;
import br.com.events.msauth.domain.entity.type.ServiceType;
import br.com.events.msauth.domain.io.person.PersonRoleEnum;
import br.com.events.msauth.domain.io.person.change_email.in.ChangePersonEmailRequest;
import br.com.events.msauth.domain.io.person.change_password.in.ChangePasswordForm;
import br.com.events.msauth.domain.io.person.create.in.CreatePersonRequest;
import br.com.events.msauth.domain.io.person.generate_token.in.GenerateTokenRequest;
import br.com.events.msauth.domain.io.person.generate_token.out.GenerateTokenResponse;
import br.com.events.msauth.domain.io.person.get_authenticated_person.out.AuthenticatedPersonResponse;
import br.com.events.msauth.domain.io.person.update.in.UpdatePersonRequest;
import br.com.events.msauth.domain.io.person.update.out.UpdatePersonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * This class shows all person related endpoint on this microservice
 *
 * @author Gabriel Guimarães de Almeida
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/person")
public class PersonController implements PersonControllerDoc {

    private final PersonService service;


    /**
     * This endpoint creates a new person on the database with the given data
     *
     * @param form {@link CreatePersonRequest} form with the data to validate and save on the database
     * @param role {@link PersonRoleEnum} which profile the new person will have
     * @return {@link ResponseEntity}<{@link URI}> response entity with the URI that you can access the created data
     */
    @Override
    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody @Valid CreatePersonRequest form,
            @RequestHeader("role") PersonRoleEnum role
    ) {
        service.createPerson(form, role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PostMapping("/token")
    public ResponseEntity<GenerateTokenResponse> generateToken(
            @RequestBody @Valid GenerateTokenRequest personTokenForm
    ) {
        return ResponseEntity.ok(service.generateToken(personTokenForm));
    }

    @Override
    @PatchMapping("/change-password/{uuid}")
    public ResponseEntity<Void> changePassword(
            @PathVariable("uuid") String emailValidationUuid, @RequestBody @Valid ChangePasswordForm changePasswordForm
    ) {
        service.changePassword(emailValidationUuid, changePasswordForm);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<Void> update(
            @PathVariable("uuid") String personUuid,
            @RequestBody @Valid final UpdatePersonRequest updatePersonRequest
    ) {
        service.update(personUuid, updatePersonRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PatchMapping("/change-email/{uuid}")
    public ResponseEntity<UpdatePersonResult> changeEmail(
            @PathVariable("uuid") String emailValidationUuid,
            @RequestBody @Valid ChangePersonEmailRequest changePersonEmailRequest
    ) {
        service.changeEmail(emailValidationUuid, changePersonEmailRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<AuthenticatedPersonResponse> getAuthenticatedPersonInformation() {
        var result = service.getAuthenticatedPerson();
        return ResponseEntity.ok(result);
    }

    @Override
    @PostMapping("/add-service/{serviceUuid}/{serviceType}")
    public ResponseEntity<Void> addServiceToPerson(
            @PathVariable("serviceUuid") String serviceUuid, @PathVariable("serviceType") ServiceType serviceType
    ) {
        service.addServiceToPerson(serviceUuid, serviceType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/owner/{serviceType}/{serviceUuid}")
    public ResponseEntity<Void> checkIfPersonIsServiceOwner(
            @PathVariable("serviceType") ServiceType serviceType,
            @PathVariable("serviceUuid") String serviceUuid
    ) {
        service.checkIfPersonIsServiceOwner(serviceType, serviceUuid);
        return ResponseEntity.noContent().build();
    }
}
