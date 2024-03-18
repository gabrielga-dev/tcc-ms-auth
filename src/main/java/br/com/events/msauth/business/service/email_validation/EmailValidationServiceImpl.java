package br.com.events.msauth.business.service.email_validation;

import br.com.events.msauth.business.exception.email_validation.EmailValidationNotFoundException;
import br.com.events.msauth.business.exception.person.NoPersonFoundByGivenEmailException;
import br.com.events.msauth.business.process.person.PersonActionProcessCaller;
import br.com.events.msauth.business.use_case.email_request.BuildEmailRequestUseCase;
import br.com.events.msauth.business.use_case.email_request.SendEmailRequestUseCase;
import br.com.events.msauth.business.use_case.email_validation.CreateEmailValidationUseCase;
import br.com.events.msauth.business.use_case.email_validation.FindEmailValidationUseCase;
import br.com.events.msauth.business.use_case.email_validation.ValidateEmailValidationUseCase;
import br.com.events.msauth.business.use_case.person.FindPersonUseCase;
import br.com.events.msauth.business.use_case.person.SavePersonUseCase;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.io.process.ProcessActionType;
import br.com.events.msauth.domain.io.process.ProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailValidationServiceImpl implements EmailValidationService {

    private final SavePersonUseCase savePersonUseCase;
    private final FindPersonUseCase findPersonUseCase;

    private final CreateEmailValidationUseCase createEmailValidationUseCase;
    private final FindEmailValidationUseCase findEmailValidationUseCase;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;

    private final BuildEmailRequestUseCase buildEmailRequestUseCase;
    private final SendEmailRequestUseCase sendEmailRequestUseCase;

    private final PersonActionProcessCaller personRoleActivationProcessCaller;

    @Override
    public void checkIfEmailValidationExistsAndNotValidated(String emailValidationUuid) {
        var emailValidation = findEmailValidationUseCase.byUuid(emailValidationUuid)
                .orElseThrow(EmailValidationNotFoundException::new);
        if (emailValidation.isValidated()) {
            throw new EmailValidationNotFoundException();
        }
    }

    @Override
    public void validateEmailValidation(EmailValidationType type, String emailValidationUuid) {
        //find email validation
        var emailValidation = findEmailValidationUseCase.byUuidAndType(emailValidationUuid, type)
                .orElseThrow(EmailValidationNotFoundException::new);

        //validate email validation
        validateEmailValidationUseCase.execute(emailValidation);

        //set person as active
        var person = emailValidation.getPerson();
        var validationStatus = person.getEmailConfirmations()
                .stream()
                .allMatch(validation -> validation.isValidated() && Objects.nonNull(validation.getValidationDate()));
        person.setActive(validationStatus);

        //save person
        person = savePersonUseCase.execute(person);

        var dto = new ProcessDTO<>(
                ProcessActionType.from(type).orElseThrow(),
                person
        );
        personRoleActivationProcessCaller.submitToProcesses(dto);
    }

    @Override
    public void createPasswordChangeRequest(String personEmail) {
        //build and save email validation
        var person = findPersonUseCase.byEmail(personEmail).orElseThrow(NoPersonFoundByGivenEmailException::new);
        var emailValidation = createEmailValidationUseCase.fromPasswordChange(person);

        //send email
        var emailRequest = buildEmailRequestUseCase.fromChangePasswordRequest(emailValidation.getUuid(), person);
        sendEmailRequestUseCase.send(emailRequest);
    }

    @Override
    public void createEmailChangeRequest(String personUuid) {
        //build and save email validation
        var person = findPersonUseCase.byUuid(personUuid).orElseThrow(NoPersonFoundByGivenEmailException::new);
        var emailValidation = createEmailValidationUseCase.fromEmailChange(person);

        //send email
        var emailRequest = buildEmailRequestUseCase.fromEmailChange(emailValidation.getUuid(), person);
        sendEmailRequestUseCase.send(emailRequest);
    }
}
