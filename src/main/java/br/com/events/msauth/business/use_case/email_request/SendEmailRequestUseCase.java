package br.com.events.msauth.business.use_case.email_request;

import br.com.events.msauth.adapters.dispatcher.KafkaDispatcher;
import br.com.events.msauth.domain.io.kafka_messages.RawEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.person.create.PersonCreationEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.person.email_change.EmailChangeRequestEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.person.email_changed.EmailChangedEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.person.password_change.PasswordChangeRequestEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendEmailRequestUseCase {

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    private final KafkaDispatcher<EmailChangedEmailRequest> emailChangedDispatcher;
    private final KafkaDispatcher<EmailChangeRequestEmailRequest> emailChangeRequestDispatcher;
    private final KafkaDispatcher<PasswordChangeRequestEmailRequest> passwordChangeRequestDispatcher;
    private final KafkaDispatcher<PersonCreationEmailRequest> personCreationDispatcher;

    public void fromEmailChanged(RawEmailRequest emailRequest){
        var message = new EmailChangedEmailRequest(emailRequest);

        emailChangedDispatcher.send(
                emailKafkaTopic,
                emailRequest.getKeyAndValues().get("emailValidationUuid"),
                message,
                (x, y) -> log.info("Email message dispatched!")
        );
    }

    public void fromEmailChange(RawEmailRequest emailRequest){
        var message = new EmailChangeRequestEmailRequest(emailRequest);

        emailChangeRequestDispatcher.send(
                emailKafkaTopic,
                message.getEmailValidationUuid(),
                message,
                (x, y) -> log.info("Email message dispatched!")
        );
    }

    public void fromPasswordChangeRequest(RawEmailRequest emailRequest){
        var message = new PasswordChangeRequestEmailRequest(emailRequest);

        passwordChangeRequestDispatcher.send(
                emailKafkaTopic,
                message.getEmailValidationUuid(),
                message,
                (x, y) -> log.info("Email message dispatched!")
        );
    }

    public void fromPersonCreation(RawEmailRequest emailRequest){
        var message = new PersonCreationEmailRequest(emailRequest);

        personCreationDispatcher.send(
                emailKafkaTopic,
                message.getEmailValidationUuid(),
                message,
                (x, y) -> log.info("Email message dispatched!")
        );
    }
}
