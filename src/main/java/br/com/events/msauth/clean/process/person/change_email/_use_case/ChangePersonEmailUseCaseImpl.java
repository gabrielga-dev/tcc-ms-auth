package br.com.events.msauth.clean.process.person.change_email._use_case;

import br.com.events.msauth.clean.domain.dto.email_validation.validate.in.ValidateEmailValidationDTO;
import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.domain.dto.person.change_email.ChangeEmailDTO;
import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.clean.domain.type.EmailRequestType;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.change_email.in.ChangePersonEmailForm;
import br.com.events.msauth.clean.process.email_validation.find_by_uuid._use_case.interfaces.FindEmailValidationByUuidUseCase;
import br.com.events.msauth.clean.process.email_validation.validate._use_case.interfaces.ValidateEmailValidationUseCase;
import br.com.events.msauth.clean.process.kafka._use_case.interfaces.DispatchEmailValidationKafkaMessageUseCase;
import br.com.events.msauth.clean.process.person.change_email._use_case.interfaces.ChangePersonEmailUseCase;
import br.com.events.msauth.clean.process.person.change_email.validations.ChangeEmailValidationCaller;
import br.com.events.msauth.clean.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * This class implements {@link ChangePersonEmailUseCase} to change a person email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangePersonEmailUseCaseImpl implements ChangePersonEmailUseCase {

    private final ChangeEmailValidationCaller changePersonEmailValidator;
    private final FindEmailValidationByUuidUseCase findEmailValidationByUuidUseCase;

    private final PersonRepository personRepository;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;

    private final DispatchEmailValidationKafkaMessageUseCase dispatchEmailValidationKafkaMessageUseCase;

    @Override
    public void execute(String emailValidationUuid, final ChangePersonEmailForm form) {
        this.validate(emailValidationUuid, form);

        var emailValidation = findEmailValidationByUuidUseCase.execute(emailValidationUuid);

        var person = this.setNewEmail(emailValidation, form);

        this.validateEmailValidation(emailValidationUuid);

        this.sendEmail(emailValidationUuid, person);
    }

    private void validate(String emailValidationUuid, ChangePersonEmailForm form) {
        var changeEmailDto = ChangeEmailDTO
                .builder()
                .emailValidationUuid(emailValidationUuid)
                .newEmail(form.getNewEmail())
                .build();
        changePersonEmailValidator.validate(changeEmailDto);
    }

    private Person setNewEmail(EmailValidation emailValidation, ChangePersonEmailForm form) {
        var person = emailValidation.getPerson();

        person.setEmail(form.getNewEmail());
        return personRepository.save(person);
    }

    private void validateEmailValidation(String emailValidationUuid) {
        var validationForm = ValidateEmailValidationDTO
                .builder()
                .emailValidationType(EmailValidationType.EMAIL_CHANGE)
                .emailValidationUuid(emailValidationUuid)
                .build();

        validateEmailValidationUseCase.execute(validationForm);
    }

    private void sendEmail(String emailValidationUuid, Person person) {
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("emailValidationUuid", emailValidationUuid);

        var personCreationKafkaMessage = RawEmailRequest
                .builder()
                .keyAndValues(keyAndValues)
                .type(EmailRequestType.EMAIL_CHANGED)
                .build();

        dispatchEmailValidationKafkaMessageUseCase.execute(personCreationKafkaMessage);
    }
}
