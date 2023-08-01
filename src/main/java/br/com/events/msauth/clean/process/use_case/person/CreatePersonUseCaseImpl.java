package br.com.events.msauth.clean.process.use_case.person;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.clean.domain.type.EmailRequestType;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.clean.process.process.person.create.validation.CreatePersonValidationCaller;
import br.com.events.msauth.clean.process.use_case.email_validation.interfaces.CreateEmailValidationUseCase;
import br.com.events.msauth.clean.process.use_case.kafka.interfaces.DispatchEmailValidationKafkaMessageUseCase;
import br.com.events.msauth.clean.process.use_case.person.interfaces.CreatePersonUseCase;
import br.com.events.msauth.legacy.domain.mapper.person.CreatePersonUseCaseMapper;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * This class implements the {@link CreatePersonUseCase} interface and creates a new person at database, create a new
 * email validation and send it through a kafka queue to MS-MAILER send it to the person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreatePersonUseCaseImpl implements CreatePersonUseCase {

    private final CreatePersonValidationCaller validator;

    private final PersonRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final CreateEmailValidationUseCase createEmailValidationUseCase;

    private final DispatchEmailValidationKafkaMessageUseCase dispatchEmailValidationKafkaMessageUseCase;

    @Override
    public String execute(final CreatePersonForm param) {
        validator.validate(param);

        var saved = this.persistNewPerson(param);

        var creationValidationEmail = this.createEmailValidation(saved);

        this.sendEmailValidation(saved, creationValidationEmail);

        return saved.getUuid();
    }

    private Person persistNewPerson(CreatePersonForm param) {
        var toSave = CreatePersonUseCaseMapper.convertToEntity(param);
        var encryptedPassword = passwordEncoder.encode(param.getPassword());
        toSave.setPassword(encryptedPassword);
        toSave.setCreationDate(LocalDateTime.now());

        return repository.save(toSave);
    }

    private EmailValidation createEmailValidation(Person person) {
        var toSave = new EmailValidation();
        toSave.setPerson(person);
        toSave.setType(EmailValidationType.PERSON_CREATION);
        toSave.setCreationDate(LocalDateTime.now());

        return createEmailValidationUseCase.execute(person.getUuid(), toSave);
    }

    private void sendEmailValidation(Person person, EmailValidation creationValidationEmail) {
        var keyAndValues = new HashMap<String, String>();
        keyAndValues.put("email", person.getEmail());
        keyAndValues.put("personFirstName", person.getFirstName());
        keyAndValues.put("personLastName", person.getLastName());
        keyAndValues.put("emailValidationUuid", creationValidationEmail.getUuid());

        var personCreationKafkaMessage = RawEmailRequest
                .builder()
                .keyAndValues(keyAndValues)
                .type(EmailRequestType.PERSON_CREATION)
                .build();

        dispatchEmailValidationKafkaMessageUseCase.execute(personCreationKafkaMessage);
    }
}
