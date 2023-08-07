package br.com.events.msauth.clean.infrastructure.controller;

import br.com.events.msauth.clean.domain.entity.type.ServiceType;
import br.com.events.msauth.clean.infrastructure.controller.doc.PersonControllerDoc;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.change_email.in.ChangePersonEmailForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.change_password.in.ChangePasswordForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.generate_token.in.GeneratePersonTokenForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.generate_token.out.GeneratePersonTokenResult;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.update.in.UpdatePersonForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.update.out.UpdatePersonResult;
import br.com.events.msauth.clean.process.person.add_service_to_person._use_case.interfaces.AddServiceToPersonUseCase;
import br.com.events.msauth.clean.process.person.change_email._use_case.interfaces.ChangePersonEmailUseCase;
import br.com.events.msauth.clean.process.person.change_password._use_case.interfaces.ChangePersonPasswordUseCase;
import br.com.events.msauth.clean.process.person.check_if_person_is_owner._use_case.interfaces.CheckIfPersonIsServiceOwnerUseCase;
import br.com.events.msauth.clean.process.person.create._use_case.interfaces.CreatePersonUseCase;
import br.com.events.msauth.clean.process.person.generate_token._use_case.interfaces.GeneratePersonTokenUseCase;
import br.com.events.msauth.clean.process.person.get_authenticated_person._use_case.interfaces.GetAuthenticatedPersonInformationUseCase;
import br.com.events.msauth.clean.process.person.update._use_case.interfaces.UpdatePersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    private final GeneratePersonTokenUseCase generatePersonTokenUseCase;
    private final ChangePersonPasswordUseCase changePersonPasswordUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final ChangePersonEmailUseCase changePersonEmailUseCase;
    private final GetAuthenticatedPersonInformationUseCase getAuthenticatedPersonInformationUseCase;
    private final AddServiceToPersonUseCase addServiceToPersonUseCase;
    private final CheckIfPersonIsServiceOwnerUseCase checkIfPersonIsServiceOwnerUseCase;

    /**
     * This endpoint creates a new person on the database with the given data
     *
     * @param form {@link CreatePersonForm} form with the data to validate and save on the database
     * @return {@link ResponseEntity}<{@link URI}> response entity with the URI that you can access the created data
     */
    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid CreatePersonForm form) {
        var result = createPersonUseCase.execute(form);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).build();
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

    @Override
    @PatchMapping("/change-password/{uuid}")
    public ResponseEntity<Void> changePassword(
            @PathVariable("uuid") String emailValidationUuid, @RequestBody @Valid ChangePasswordForm changePasswordForm
    ) {
        changePersonPasswordUseCase.execute(emailValidationUuid, changePasswordForm);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<UpdatePersonResult> update(
            @PathVariable("uuid") String personUuid,
            @RequestBody @Valid final UpdatePersonForm updatePersonForm
    ) {
        var result = updatePersonUseCase.execute(personUuid, updatePersonForm);
        return ResponseEntity.ok(result);
    }

    @Override
    @PatchMapping("/change-email/{uuid}")
    public ResponseEntity<UpdatePersonResult> changeEmail(
            @PathVariable("uuid") String emailValidationUuid,
            @RequestBody @Valid ChangePersonEmailForm changePersonEmailForm
    ) {
        changePersonEmailUseCase.execute(emailValidationUuid, changePersonEmailForm);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<GetAuthenticatedPersonInformationResult> getAuthenticatedPersonInformation() {
        var result = getAuthenticatedPersonInformationUseCase.execute();
        return ResponseEntity.ok(result);
    }

    @Override
    @PostMapping("/add-service/{serviceUuid}/{serviceType}")
    public ResponseEntity<Void> addServiceToPerson(
            @PathVariable("serviceUuid") String serviceUuid, @PathVariable("serviceType") ServiceType serviceType
    ) {
        addServiceToPersonUseCase.execute(serviceType, serviceUuid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/owner/{serviceType}/{serviceUuid}")
    public ResponseEntity<Void> checkIfPersonIsServiceOwner(
            @PathVariable("serviceType") ServiceType serviceType,
            @PathVariable("serviceUuid") String serviceUuid
    ) {
        checkIfPersonIsServiceOwnerUseCase.execute(serviceType, serviceUuid);
        return ResponseEntity.noContent().build();
    }
}
