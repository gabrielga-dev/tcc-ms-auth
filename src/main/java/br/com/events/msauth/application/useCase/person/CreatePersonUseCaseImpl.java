package br.com.events.msauth.application.useCase.person;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.events.msauth.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonResult;
import br.com.events.msauth.domain.mapper.PersonMapper;
import br.com.events.msauth.domain.message.PersonCreationEmailRequestMessage;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.person.CreatePersonUseCase;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class implements the {@link CreatePersonUseCase} interface to create a new person
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePersonUseCaseImpl implements CreatePersonUseCase {

    private final PersonRepository repository;
    private final PersonCreationValidator validator;

    private final KafkaDispatcher<PersonCreationEmailRequestMessage> testEmailKafkaDispatcher;

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    @Override
    public CreatePersonResult execute(final CreatePersonForm param) {

        validator.validate(param);

        var toSave = PersonMapper.convertToEntity(param);
        toSave.setCreationDate(LocalDateTime.now());

        var saved = repository.save(toSave);

        sendMessages(saved);

        return PersonMapper.convertToResult(saved);
    }

    private void sendMessages(final Person saved) {
        var message = PersonCreationEmailRequestMessage
            .builder()
            .templateId(1L)
            .to(saved.getEmail())
            .test(saved.getUuid())
            .build();

        testEmailKafkaDispatcher.send(
            emailKafkaTopic,
            saved.getUuid(),
            message,
            (x, y) -> log.info("Email message dispatched!")
        );
    }
}
