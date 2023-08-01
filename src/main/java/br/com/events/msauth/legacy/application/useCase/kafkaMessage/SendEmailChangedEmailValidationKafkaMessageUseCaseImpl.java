package br.com.events.msauth.legacy.application.useCase.kafkaMessage;

import br.com.events.msauth.legacy.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendEmailChangedEmailValidationKafkaMessage.in.SendEmailChangedEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.legacy.domain.mapper.kafkaMessage.SendEmailChangedEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.clean.domain.dto.kafka.message.EmailChangedEmailRequestKafkaMessage;
import br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage.SendEmailChangedEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class implements {@link SendEmailChangedEmailValidationKafkaMessageUseCase} interface and sends a kafka message
 * to email the person telling that the email has changed
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SendEmailChangedEmailValidationKafkaMessageUseCaseImpl implements
    SendEmailChangedEmailValidationKafkaMessageUseCase {

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    private final KafkaDispatcher<EmailChangedEmailRequestKafkaMessage> emailKafkaDispatcher;

    @Override
    public Void execute(final SendEmailChangedEmailValidationKafkaMessageUseCaseForm param) {
        var message = SendEmailChangedEmailValidationKafkaMessageUseCaseMapper.convertToKafkaMessage(param);

        emailKafkaDispatcher.send(
            emailKafkaTopic,
            "email_has_changed",
            message,
            (x, y) -> log.info("Email message dispatched!")
        );

        return null;
    }
}
