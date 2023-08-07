package br.com.events.msauth.clean.infrastructure.controller;

import br.com.events.msauth.clean.infrastructure.controller.entity.email_validation.change_email_request.in.CreateEmailChangeRequestForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.email_validation.change_password_request.in.CreatePasswordChangeRequestForm;
import br.com.events.msauth.clean.infrastructure.controller.doc.EmailValidationControllerDoc;
import br.com.events.msauth.clean.process.email_validation.email_validation_exists_and_not_validated._use_case.interfaces.CheckIfEmailValidationExistsAndIsNotValidatedUseCase;
import br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces.CreateEmailChangeEmailValidationUseCase;
import br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces.CreatePasswordChangeEmailValidationUseCase;
import br.com.events.msauth.clean.process.email_validation.validate._use_case.interfaces.ValidatePersonCreationEmailValidationUseCase;
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

    private final ValidatePersonCreationEmailValidationUseCase validatePersonCreationEmailValidationUseCase;
    private final CheckIfEmailValidationExistsAndIsNotValidatedUseCase checkIfEmailValidationExistsAndIsNotValidatedUseCase;
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
