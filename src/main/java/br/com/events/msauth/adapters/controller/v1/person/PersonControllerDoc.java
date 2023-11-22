package br.com.events.msauth.adapters.controller.v1.person;

import br.com.events.msauth.domain.entity.type.ServiceType;
import br.com.events.msauth.domain.io.person.PersonRoleEnum;
import br.com.events.msauth.domain.io.person.change_email.in.ChangePersonEmailRequest;
import br.com.events.msauth.domain.io.person.change_password.in.ChangePasswordForm;
import br.com.events.msauth.domain.io.person.create.in.CreatePersonRequest;
import br.com.events.msauth.domain.io.person.generate_token.in.GenerateTokenRequest;
import br.com.events.msauth.domain.io.person.generate_token.out.GenerateTokenResponse;
import br.com.events.msauth.domain.io.person.get_authenticated_person.out.PersonResponse;
import br.com.events.msauth.domain.io.person.get_authenticated_person.out.PersonWithRolesResponse;
import br.com.events.msauth.domain.io.person.update.in.UpdatePersonRequest;
import br.com.events.msauth.domain.io.person.update.out.UpdatePersonResult;
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
public interface PersonControllerDoc {

    @ApiOperation(value = "Creates a new person")
    ResponseEntity<Void> create(CreatePersonRequest form, PersonRoleEnum role);

    @ApiOperation(value = "Generate a person's JWT token")
    ResponseEntity<GenerateTokenResponse> generateToken(GenerateTokenRequest personTokenForm);

    @ApiOperation(value = "Change password")
    ResponseEntity<Void> changePassword(String emailValidationUuid, ChangePasswordForm changePasswordForm);

    @ApiOperation(value = "Update a person with the given data")
    ResponseEntity<Void> update(String personUuid, UpdatePersonRequest updatePersonRequest);

    @ApiOperation(value = "Update a person email")
    ResponseEntity<UpdatePersonResult> changeEmail(String personUuid, ChangePersonEmailRequest changePersonEmailRequest);

    @ApiOperation(value = "Get the information of the authenticated person")
    ResponseEntity<PersonWithRolesResponse> getAuthenticatedPersonInformation();


    @ApiOperation(value = "Add the given service to the authenticated person")
    ResponseEntity<Void> addServiceToPerson(String serviceUuid, ServiceType serviceType);


    @ApiOperation(value = "Check if the authenticated person is the owner of the given service")
    ResponseEntity<Void> checkIfPersonIsServiceOwner(ServiceType serviceType, String serviceUuid);


    @ApiOperation(value = "Find a person by it's CPF")
    ResponseEntity<PersonResponse> findByCpf(String personCpf);
}
