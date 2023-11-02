package br.com.events.msauth.business.service.person;

import br.com.events.msauth.domain.entity.type.ServiceType;
import br.com.events.msauth.domain.io.person.PersonRoleEnum;
import br.com.events.msauth.domain.io.person.change_email.in.ChangePersonEmailRequest;
import br.com.events.msauth.domain.io.person.change_password.in.ChangePasswordForm;
import br.com.events.msauth.domain.io.person.create.in.CreatePersonRequest;
import br.com.events.msauth.domain.io.person.generate_token.in.GenerateTokenRequest;
import br.com.events.msauth.domain.io.person.generate_token.out.GenerateTokenResponse;
import br.com.events.msauth.domain.io.person.get_authenticated_person.out.AuthenticatedPersonResponse;
import br.com.events.msauth.domain.io.person.update.in.UpdatePersonRequest;

public interface PersonService {

    void createPerson(CreatePersonRequest request, PersonRoleEnum roleEnum);

    void update(String personUuid, UpdatePersonRequest updatePersonRequest);

    GenerateTokenResponse generateToken(GenerateTokenRequest tokenRequest);

    void changePassword(String emailValidationUuid, ChangePasswordForm changePasswordForm);

    void changeEmail(String emailValidationUuid, ChangePersonEmailRequest request);

    AuthenticatedPersonResponse getAuthenticatedPerson();

    void addServiceToPerson(String serviceUuid, ServiceType serviceType);

    void checkIfPersonIsServiceOwner(ServiceType serviceType, String serviceUuid);
}
