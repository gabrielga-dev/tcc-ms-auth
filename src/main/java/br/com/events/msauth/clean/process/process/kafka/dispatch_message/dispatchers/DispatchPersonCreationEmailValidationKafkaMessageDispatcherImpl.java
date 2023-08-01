package br.com.events.msauth.clean.process.process.kafka.dispatch_message.dispatchers;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.domain.type.EmailTemplateIdType;
import br.com.events.msauth.clean.domain.type.EmailRequestType;
import br.com.events.msauth.clean.process.process.kafka.dispatch_message.KafkaMessageDispatcher;
import br.com.events.msauth.legacy.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.clean.domain.dto.kafka.message.PersonCreationEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DispatchPersonCreationEmailValidationKafkaMessageDispatcherImpl implements KafkaMessageDispatcher {

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    private final KafkaDispatcher<PersonCreationEmailRequest> emailKafkaDispatcher;

    @Override
    public boolean isAccepted(RawEmailRequest toCheck) {
        return Objects.equals(EmailRequestType.PERSON_CREATION, toCheck.getType());
    }

    @Override
    public Void process(RawEmailRequest toProcess) {
        var message = PersonCreationEmailRequest
                .builder()
                .templateId(EmailTemplateIdType.PERSON_CREATION_EMAIL_VALIDATION.getId())
                .to(toProcess.getKeyAndValues().get("email"))
                .personFirstName(toProcess.getKeyAndValues().get("personFirstName"))
                .personLastName(toProcess.getKeyAndValues().get("personLastName"))
                .emailValidationUuid(toProcess.getKeyAndValues().get("emailValidationUuid"))
                .build();


        emailKafkaDispatcher.send(
                emailKafkaTopic,
                message.getEmailValidationUuid(),
                message,
                (x, y) -> log.info("Email message dispatched!")
        );

        return null;
    }
}
