package br.com.events.msauth.application.useCase.kafkaMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.events.msauth.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.domain.form.kafkaMessage.sendEmailChangedEmailValidationKafkaMessage.in.SendEmailChangedEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.domain.mapper.kafkaMessage.SendEmailChangedEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.domain.message.EmailChangedEmailRequestKafkaMessage;
import br.com.events.msauth.infrastructure.useCase.kafkaMessage.SendEmailChangedEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
