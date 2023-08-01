package br.com.events.msauth.legacy.application.useCase.kafkaMessage;

import br.com.events.msauth.legacy.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendPasswordChangeEmailValidationKafkaMessageUseCase.in.SendPasswordChangeEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.legacy.domain.mapper.kafkaMessage.SendPasswordChangeEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.clean.domain.dto.kafka.PasswordChangeEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage.SendPasswordChangeEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class sends a kafka message about password change
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SendPasswordChangeEmailValidationKafkaMessageUseCaseImpl implements
        SendPasswordChangeEmailValidationKafkaMessageUseCase {

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    private final KafkaDispatcher<PasswordChangeEmailValidationEmailRequestKafkaMessage> emailKafkaDispatcher;

    @Override
    public Void execute(final SendPasswordChangeEmailValidationKafkaMessageUseCaseForm param) {
        var message = SendPasswordChangeEmailValidationKafkaMessageUseCaseMapper.convertToKafkaMessage(param);

        emailKafkaDispatcher.send(
            emailKafkaTopic,
            message.getEmailValidationUuid(),
            message,
            (x, y) -> log.info("Email message dispatched!")
        );

        return null;
    }
}
