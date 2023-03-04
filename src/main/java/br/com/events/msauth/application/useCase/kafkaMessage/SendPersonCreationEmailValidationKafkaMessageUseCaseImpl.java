package br.com.events.msauth.application.useCase.kafkaMessage;

import br.com.events.msauth.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.domain.form.kafkaMessage.sendPersonCreationEmailValidationKafkaMessage.in.SendPersonCreationEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.domain.mapper.kafkaMessage.SendPersonCreationEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.domain.message.PersonCreationEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.infrastructure.useCase.kafkaMessage.SendPersonCreationEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link SendPersonCreationEmailValidationKafkaMessageUseCase} interface to send, at kafka
 * queue, an email validation message at person creation
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SendPersonCreationEmailValidationKafkaMessageUseCaseImpl implements
    SendPersonCreationEmailValidationKafkaMessageUseCase {

    @Value("${kafka.topic.email}")
    private String emailKafkaTopic;

    private final KafkaDispatcher<PersonCreationEmailValidationEmailRequestKafkaMessage> emailKafkaDispatcher;

    @Override
    public Void execute(final SendPersonCreationEmailValidationKafkaMessageUseCaseForm param) {
        var message = SendPersonCreationEmailValidationKafkaMessageUseCaseMapper.convertToKafkaMessage(param);

        emailKafkaDispatcher.send(
            emailKafkaTopic,
            message.getEmailValidationUuid(),
            message,
            (x, y) -> log.info("Email message dispatched!")
        );

        return null;
    }
}
