package br.com.events.msauth.adapters.controller.v1.email_validation;

import br.com.events.msauth.business.service.email_validation.EmailValidationService;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.io.email_validation.in.CreateEmailChangeRequestForm;
import br.com.events.msauth.domain.io.email_validation.in.CreatePasswordChangeRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/email-validation")
public class EmailValidationController implements EmailValidationControllerDoc {

    private final EmailValidationService emailValidationService;

    @Override
    @GetMapping("/{validationUuid}")
    public ResponseEntity<Void> checkIfEmailValidationExistsAndNotValidated(
            @PathVariable("validationUuid") String emailValidationUuid
    ) {
        emailValidationService.checkIfEmailValidationExistsAndNotValidated(emailValidationUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/person-creation/{validationUuid}")
    public ResponseEntity<Void> validatePersonCreationEmailValidation(
            @PathVariable("validationUuid") String emailValidationUuid
    ) {
        emailValidationService.validateEmailValidation(EmailValidationType.PERSON_CREATION, emailValidationUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/change-password/request")
    public ResponseEntity<Void> createPasswordChangeRequest(
            @RequestBody @Valid CreatePasswordChangeRequestForm passwordChangeRequestForm
    ) {
        emailValidationService.createPasswordChangeRequest(passwordChangeRequestForm.getEmail());
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/change-email/request")
    public ResponseEntity<Void> createEmailChangeRequest(
            @RequestBody @Valid CreateEmailChangeRequestForm createEmailChangeRequestForm
    ) {
        emailValidationService.createEmailChangeRequest(createEmailChangeRequestForm.getUuid());
        return ResponseEntity.noContent().build();
    }
}
