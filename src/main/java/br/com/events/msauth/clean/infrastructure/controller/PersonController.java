package br.com.events.msauth.clean.infrastructure.controller;

import br.com.events.msauth.clean.domain.entity.type.ServiceType;
import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangePersonEmailForm;
import br.com.events.msauth.legacy.domain.form.person.changePassword.in.ChangePasswordForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.legacy.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.legacy.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import br.com.events.msauth.legacy.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationResult;
import br.com.events.msauth.legacy.domain.form.person.update.in.UpdatePersonForm;
import br.com.events.msauth.legacy.domain.form.person.update.out.UpdatePersonResult;
import br.com.events.msauth.legacy.domain.mapper.person.AddServiceToPersonUseCaseMapper;
import br.com.events.msauth.legacy.domain.mapper.person.ChangePersonEmailUseCaseMapper;
import br.com.events.msauth.legacy.domain.mapper.person.ChangePersonPasswordUseCaseMapper;
import br.com.events.msauth.legacy.domain.mapper.person.GetAuthenticatedPersonInformationUseCaseMapper;
import br.com.events.msauth.legacy.domain.mapper.person.UpdatePersonUseCaseMapper;
import br.com.events.msauth.clean.infrastructure.controller.doc.PersonControllerDoc;
import br.com.events.msauth.legacy.infrastructure.useCase.person.AddServiceToPersonUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.ChangePersonEmailUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.ChangePersonPasswordUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.CheckIfPersonIsServiceOwnerUseCase;
import br.com.events.msauth.clean.process.use_case.person.interfaces.CreatePersonUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.GeneratePersonTokenUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.GetAuthenticatedPersonInformationUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.UpdatePersonUseCase;
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
        var useCaseForm = ChangePersonPasswordUseCaseMapper.convertToUseCaseForm(changePasswordForm);
        useCaseForm.setEmailValidationUuid(emailValidationUuid);

        changePersonPasswordUseCase.execute(useCaseForm);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<UpdatePersonResult> update(
        @PathVariable("uuid") String personUuid,
        @RequestBody @Valid final UpdatePersonForm updatePersonForm
    ) {
        var useCaseForm = UpdatePersonUseCaseMapper.convertToUseCaseForm(updatePersonForm);
        useCaseForm.setPersonUuid(personUuid);

        var result = updatePersonUseCase.execute(useCaseForm);
        return ResponseEntity.ok(result);
    }

    @Override
    @PatchMapping("/change-email/{uuid}")
    public ResponseEntity<UpdatePersonResult> changeEmail(
        @PathVariable("uuid") String emailValidationUuid,
        @RequestBody @Valid ChangePersonEmailForm changePersonEmailForm
    ) {
        var useCaseForm = ChangePersonEmailUseCaseMapper.convertToUseCaseForm(changePersonEmailForm);
        useCaseForm.setEmailValidationUuid(emailValidationUuid);

        changePersonEmailUseCase.execute(useCaseForm);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<GetAuthenticatedPersonInformationResult> getAuthenticatedPersonInformation() {
        var result = getAuthenticatedPersonInformationUseCase.execute(null);
        var mappedResult = GetAuthenticatedPersonInformationUseCaseMapper.convertToResult(result);

        return ResponseEntity.ok(mappedResult);
    }

    @Override
    @PostMapping("/add-service/{serviceUuid}/{serviceType}")
    public ResponseEntity<Void> addServiceToPerson(
        @PathVariable("serviceUuid") String serviceUuid, @PathVariable("serviceType") ServiceType serviceType
    ) {
        var useCaseForm = AddServiceToPersonUseCaseMapper.toUseCaseForm(serviceUuid, serviceType);

        addServiceToPersonUseCase.execute(useCaseForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/owner/{serviceUuid}")
    public ResponseEntity<Void> checkIfPersonIsServiceOwner(@PathVariable("serviceUuid") String serviceUuid) {
        checkIfPersonIsServiceOwnerUseCase.execute(serviceUuid);

        return ResponseEntity.noContent().build();
    }
}
