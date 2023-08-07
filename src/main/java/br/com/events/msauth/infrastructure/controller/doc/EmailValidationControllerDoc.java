package br.com.events.msauth.infrastructure.controller.doc;

import br.com.events.msauth.infrastructure.controller.entity.email_validation.change_email_request.in.CreateEmailChangeRequestForm;
import br.com.events.msauth.infrastructure.controller.entity.email_validation.change_password_request.in.CreatePasswordChangeRequestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Person Controller")
public interface EmailValidationControllerDoc {

    @ApiOperation(value = "Validate if the email validation request exists")
    ResponseEntity<Void> checkIfEmailValidationExistsAndNotValidated(String emailValidationUuid);

    @ApiOperation(value = "Validate the person's email when a it just got created")
    ResponseEntity<Void> validatePersonCreationEmailValidation(String emailValidationUuid);

    @ApiOperation(value = "Creates a new password change request")
    ResponseEntity<Void> createPasswordChangeRequest(
        CreatePasswordChangeRequestForm passwordChangeRequestForm
    );

    @ApiOperation(value = "Creates a new email change request")
    ResponseEntity<Void> createEmailChangeRequest(
        CreateEmailChangeRequestForm passwordChangeRequestForm
    );
}
