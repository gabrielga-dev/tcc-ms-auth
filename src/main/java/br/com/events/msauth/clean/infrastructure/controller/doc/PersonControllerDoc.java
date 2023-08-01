package br.com.events.msauth.clean.infrastructure.controller.doc;

import br.com.events.msauth.clean.domain.entity.type.ServiceType;
import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangePersonEmailForm;
import br.com.events.msauth.legacy.domain.form.person.changePassword.in.ChangePasswordForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.legacy.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.legacy.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import br.com.events.msauth.legacy.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationResult;
import br.com.events.msauth.legacy.domain.form.person.update.in.UpdatePersonForm;
import br.com.events.msauth.legacy.domain.form.person.update.out.UpdatePersonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Person Controller")
public interface PersonControllerDoc {

    @ApiOperation(value = "Creates a new person")
    ResponseEntity<URI> create(CreatePersonForm form);

    @ApiOperation(value = "Generate a person's JWT token")
    ResponseEntity<GeneratePersonTokenResult> generateToken(GeneratePersonTokenForm personTokenForm);

    @ApiOperation(value = "Change password")
    ResponseEntity<Void> changePassword(String emailValidationUuid, ChangePasswordForm changePasswordForm);

    @ApiOperation(value = "Update a person with the given data")
    ResponseEntity<UpdatePersonResult> update(String personUuid, UpdatePersonForm updatePersonForm);

    @ApiOperation(value = "Update a person email")
    ResponseEntity<UpdatePersonResult> changeEmail(String personUuid, ChangePersonEmailForm changePersonEmailForm);

    @ApiOperation(value = "Get the information of the authenticated person")
    ResponseEntity<GetAuthenticatedPersonInformationResult> getAuthenticatedPersonInformation();


    @ApiOperation(value = "Add the given service to the authenticated person")
    ResponseEntity<Void> addServiceToPerson(String serviceUuid, ServiceType serviceType);


    @ApiOperation(value = "Check if the authenticated person is the owner of the given service")
    ResponseEntity<Void> checkIfPersonIsServiceOwner(String serviceUuid);
}
