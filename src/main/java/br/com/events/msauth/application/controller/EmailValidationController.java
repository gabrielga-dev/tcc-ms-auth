package br.com.events.msauth.application.controller;

import br.com.events.msauth.domain.form.emailValidation.changeEmailRequest.CreateEmailChangeRequestForm;
import br.com.events.msauth.domain.form.emailValidation.passwordChangeRequest.in.CreatePasswordChangeRequestForm;
import br.com.events.msauth.infrastructure.controller.EmailValidationControllerDoc;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.CheckIfEmailValidationExistsAndIsNotValidatedUseCase;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.emailChange.CreateEmailChangeEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.passwordChange.CreatePasswordChangeEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.personCreation.ValidatePersonCreationEmailValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * This class shows all email validation related endpoint on this microservice
 *
 * @author Gabriel Guimarães de Almeida
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/email-validation")
public class EmailValidationController implements EmailValidationControllerDoc {

    private final CheckIfEmailValidationExistsAndIsNotValidatedUseCase checkIfEmailValidationExistsAndIsNotValidatedUseCase;
    private final ValidatePersonCreationEmailValidationUseCase validatePersonCreationEmailValidationUseCase;
    private final CreatePasswordChangeEmailValidationUseCase createPasswordChangeEmailValidationUseCase;
    private final CreateEmailChangeEmailValidationUseCase createEmailChangeEmailValidationUseCase;

    @Override
    @GetMapping("/{validationUuid}")
    public ResponseEntity<Void> checkIfEmailValidationExistsAndNotValidated(
        @PathVariable("validationUuid") String emailValidationUuid
    ) {
        checkIfEmailValidationExistsAndIsNotValidatedUseCase.execute(emailValidationUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/person-creation/{validationUuid}")
    public ResponseEntity<Void> validatePersonCreationEmailValidation(
        @PathVariable("validationUuid") String emailValidationUuid
    ) {
        validatePersonCreationEmailValidationUseCase.execute(emailValidationUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/change-password/request")
    public ResponseEntity<Void> createPasswordChangeRequest(
        @RequestBody @Valid CreatePasswordChangeRequestForm passwordChangeRequestForm
    ) {
        createPasswordChangeEmailValidationUseCase.execute(passwordChangeRequestForm.getEmail());
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/change-email/request")
    public ResponseEntity<Void> createEmailChangeRequest(
        @RequestBody @Valid CreateEmailChangeRequestForm createEmailChangeRequestForm
    ) {

        createEmailChangeEmailValidationUseCase.execute(createEmailChangeRequestForm.getUuid());

        return ResponseEntity.noContent().build();
    }
}
