package br.com.events.msauth.legacy.application.useCase.kafkaMessage;

import br.com.events.msauth.legacy.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendEmailChangeRequestEmailValidationKafkaMessage.in.SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.legacy.domain.mapper.kafkaMessage.SendEmailChangeEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.clean.domain.dto.kafka.email_request.EmailChangeRequestEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage.SendEmailChangeRequestEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class sends a kafka message to validate a email change
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SendEmailChangeRequestEmailValidationKafkaMessageUseCaseImpl implements
        SendEmailChangeRequestEmailValidationKafkaMessageUseCase {

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    private final KafkaDispatcher<EmailChangeRequestEmailValidationEmailRequestKafkaMessage> emailKafkaDispatcher;

    @Override
    public Void execute(final SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm param) {
        var message = SendEmailChangeEmailValidationKafkaMessageUseCaseMapper.convertToKafkaMessage(param);

        emailKafkaDispatcher.send(
            emailKafkaTopic,
            message.getEmailValidationUuid(),
            message,
            (x, y) -> log.info("Email message dispatched!")
        );

        return null;
    }
}
