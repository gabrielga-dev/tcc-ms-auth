package br.com.events.msauth.clean.process.kafka.dispatchers.dispatchers;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.domain.dto.kafka.email_request.EmailChangedEmailRequestKafkaMessage;
import br.com.events.msauth.clean.domain.type.EmailRequestType;
import br.com.events.msauth.clean.domain.type.EmailTemplateIdType;
import br.com.events.msauth.clean.process.kafka.dispatchers.KafkaMessageDispatcher;
import br.com.events.msauth.legacy.application.dispatcher.KafkaDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DispatchEmailChangedKafkaMessageDispatcherImpl implements KafkaMessageDispatcher {

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    private final KafkaDispatcher<EmailChangedEmailRequestKafkaMessage> emailKafkaDispatcher;

    @Override
    public boolean isAccepted(RawEmailRequest toCheck) {
        return Objects.equals(EmailRequestType.EMAIL_CHANGED, toCheck.getType());
    }

    @Override
    public Void process(RawEmailRequest toProcess) {
        var message = EmailChangedEmailRequestKafkaMessage
                .builder()
                .templateId(EmailTemplateIdType.EMAIL_CHANGED.getId())
                .to(toProcess.getKeyAndValues().get("email"))
                .personFirstName(toProcess.getKeyAndValues().get("personFirstName"))
                .personLastName(toProcess.getKeyAndValues().get("personLastName"))
                .build();


        emailKafkaDispatcher.send(
                emailKafkaTopic,
                toProcess.getKeyAndValues().get("emailValidationUuid"),
                message,
                (x, y) -> log.info("Email message dispatched!")
        );

        return null;
    }
}
