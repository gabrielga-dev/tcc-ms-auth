package br.com.events.msauth.business.service.person;

import br.com.events.msauth.business.exception.auth.InvalidCredentialsToGenerateJwtTokenException;
import br.com.events.msauth.business.exception.email_validation.EmailValidationNotFoundException;
import br.com.events.msauth.business.exception.person.ChangePasswordPasswordEqualityException;
import br.com.events.msauth.business.exception.person.CpfNotAvailableException;
import br.com.events.msauth.business.exception.person.EmailNotAvailableException;
import br.com.events.msauth.business.exception.person.NoPersonFoundByGivenCpfException;
import br.com.events.msauth.business.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.business.exception.person.NotAbleToUpdateOtherPersonInformationException;
import br.com.events.msauth.business.exception.person.PasswordNotEqualException;
import br.com.events.msauth.business.exception.person.PersonIsNotTheServiceOwnerException;
import br.com.events.msauth.business.process.person.PersonActionProcessCaller;
import br.com.events.msauth.business.service.auth.AuthenticationService;
import br.com.events.msauth.business.service.jwt.JwtTokenService;
import br.com.events.msauth.business.use_case.email_request.BuildEmailRequestUseCase;
import br.com.events.msauth.business.use_case.email_request.SendEmailRequestUseCase;
import br.com.events.msauth.business.use_case.email_validation.CreateEmailValidationUseCase;
import br.com.events.msauth.business.use_case.email_validation.FindEmailValidationUseCase;
import br.com.events.msauth.business.use_case.email_validation.ValidateEmailValidationUseCase;
import br.com.events.msauth.business.use_case.person.FindPersonUseCase;
import br.com.events.msauth.business.use_case.person.SavePersonUseCase;
import br.com.events.msauth.business.use_case.role.FindRoleUseCase;
import br.com.events.msauth.business.use_case.service.BuildServiceUseCase;
import br.com.events.msauth.business.use_case.service.FindServiceUseCase;
import br.com.events.msauth.business.use_case.service.SaveServiceUseCase;
import br.com.events.msauth.domain.entity.Person;
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
import br.com.events.msauth.domain.io.process.ProcessActionType;
import br.com.events.msauth.domain.io.process.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenService jwtTokenService;
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    private final SavePersonUseCase savePersonUseCase;
    private final FindPersonUseCase findPersonUseCase;

    private final FindRoleUseCase findRoleUseCase;

    private final BuildServiceUseCase buildServiceUseCase;
    private final SaveServiceUseCase saveServiceUseCase;
    private final FindServiceUseCase findServiceUseCase;

    private final CreateEmailValidationUseCase createEmailValidationUseCase;
    private final FindEmailValidationUseCase findEmailValidationUseCase;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;

    private final BuildEmailRequestUseCase buildEmailRequestUseCase;
    private final SendEmailRequestUseCase sendEmailRequestUseCase;

    private final PersonActionProcessCaller personRoleActivationProcessCaller;

    @Override
    public void createPerson(CreatePersonRequest request, PersonRoleEnum roleEnum) {
        //validate
        findPersonUseCase.byCpf(request.getCpf())
                .ifPresent(
                        p -> {
                            throw new CpfNotAvailableException();
                        }
                );
        findPersonUseCase.byEmail(request.getEmail())
                .ifPresent(
                        p -> {
                            throw new EmailNotAvailableException();
                        }
                );
        if (!request.getPassword().equals(request.getPasswordRepeated())) {
            throw new PasswordNotEqualException();
        }

        //save
        var role = findRoleUseCase.byId(roleEnum.getId()).orElseThrow();
        var encryptedPassword = passwordEncoder.encode(request.getPassword());
        var saved = savePersonUseCase.execute(new Person(request, encryptedPassword, role));

        //email validation
        var creationValidationEmail = createEmailValidationUseCase.fromPersonCreation(saved);

        //send email
        var emailRequest = buildEmailRequestUseCase.fromPersonCreation(saved, creationValidationEmail);
        sendEmailRequestUseCase.send(emailRequest);
    }

    @Override
    public void update(String personUuid, UpdatePersonRequest updatePersonRequest) {
        //validate
        var authenticatedPersonUuid = authenticationService.getAuthenticatedPerson().getUuid();
        if (!Objects.equals(authenticatedPersonUuid, personUuid)) {
            throw new NotAbleToUpdateOtherPersonInformationException();
        }
        var person = findPersonUseCase.byUuid(personUuid).orElseThrow(NoPersonFoundByGivenUuidException::new);

        //update
        person.setFirstName(updatePersonRequest.getFirstName());
        person.setLastName(updatePersonRequest.getLastName());
        savePersonUseCase.execute(person);

        var dto = new ProcessDTO<>(ProcessActionType.PERSON_UPDATE, person);
        personRoleActivationProcessCaller.submitToProcesses(dto);
    }

    @Override
    public GenerateTokenResponse generateToken(GenerateTokenRequest tokenRequest) {
        try {
            var authenticationForm = new UsernamePasswordAuthenticationToken(
                    tokenRequest.getUsername(), tokenRequest.getPassword()
            );

            var authentication = authenticationManager.authenticate(authenticationForm);
            var token = jwtTokenService.generateToken(authentication);

            return new GenerateTokenResponse(token);
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsToGenerateJwtTokenException();
        }
    }

    @Override
    public void changePassword(String emailValidationUuid, ChangePasswordForm changePasswordForm) {
        //validate
        var emailValidation = findEmailValidationUseCase.byUuid(emailValidationUuid)
                .orElseThrow(EmailValidationNotFoundException::new);
        if (emailValidation.isValidated() || !emailValidation.getPerson().isActive()) {
            throw new EmailValidationNotFoundException();
        }
        if (!changePasswordForm.getPassword().equals(changePasswordForm.getPasswordRepeated())) {
            throw new ChangePasswordPasswordEqualityException();
        }

        //validate email validation
        validateEmailValidationUseCase.execute(emailValidation);

        //set encrypted password
        var person = emailValidation.getPerson();
        person.setPassword(passwordEncoder.encode(changePasswordForm.getPassword()));
        savePersonUseCase.execute(person);

    }

    @Override
    public void changeEmail(String emailValidationUuid, ChangePersonEmailRequest request) {
        //validate
        if (findPersonUseCase.byEmail(request.getNewEmail()).isPresent()) {
            throw new EmailNotAvailableException();
        }
        var emailValidation = findEmailValidationUseCase.byUuid(emailValidationUuid)
                .orElseThrow(EmailValidationNotFoundException::new);
        if (emailValidation.isValidated() || !emailValidation.getPerson().isActive()) {
            throw new EmailValidationNotFoundException();
        }

        //validate email validation
        validateEmailValidationUseCase.execute(emailValidation);

        //update email
        var person = emailValidation.getPerson();
        person.setEmail(request.getNewEmail());
        savePersonUseCase.execute(person);

        //send email
        var emailRequest = buildEmailRequestUseCase.fromEmailChanged(emailValidationUuid, person);
        sendEmailRequestUseCase.send(emailRequest);

        var dto = new ProcessDTO<>(ProcessActionType.EMAIL_CHANGE, person);
        personRoleActivationProcessCaller.submitToProcesses(dto);
    }

    @Override
    public PersonWithRolesResponse getAuthenticatedPerson() {
        var authenticatedPerson = authenticationService.getAuthenticatedPerson();
        return new PersonWithRolesResponse(authenticatedPerson);
    }

    @Override
    public void addServiceToPerson(String serviceUuid, ServiceType serviceType) {
        var authenticatedPerson = authenticationService.getAuthenticatedPerson();
        var builtService = buildServiceUseCase.execute(authenticatedPerson, serviceUuid, serviceType);
        saveServiceUseCase.execute(builtService);
    }

    @Override
    public void checkIfPersonIsServiceOwner(ServiceType serviceType, String serviceUuid) {
        var authenticatedPersonUuid = authenticationService.getAuthenticatedPerson().getUuid();
        var service = findServiceUseCase.execute(serviceUuid, authenticatedPersonUuid)
                .orElseThrow(PersonIsNotTheServiceOwnerException::new);
        if (!Objects.equals(service.getType(), serviceType)) {
            throw new PersonIsNotTheServiceOwnerException();
        }
    }

    @Override
    public PersonResponse findByCpf(String personCpf) {
        var person = findPersonUseCase.byCpf(personCpf).orElseThrow(NoPersonFoundByGivenCpfException::new);

        return new PersonResponse(person);
    }

    @Override
    public PersonResponse findByUuid(String personUuid) {
        var person = findPersonUseCase.byUuid(personUuid).orElseThrow(NoPersonFoundByGivenUuidException::new);
        return new PersonResponse(person);
    }
}
