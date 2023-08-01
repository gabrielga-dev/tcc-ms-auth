package br.com.events.msauth.legacy.application.config.kafkaDispatcher;

import br.com.events.msauth.legacy.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.clean.domain.dto.kafka.EmailChangeRequestEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.clean.domain.dto.kafka.EmailChangedEmailRequestKafkaMessage;
import br.com.events.msauth.clean.domain.dto.kafka.PasswordChangeEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.clean.domain.dto.kafka.PersonCreationEmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration class creates the beans of kafka dispatchers for orders and emails
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Configuration
public class KafkaDispatcherBean {

    @Value("${kafka.port}")
    private String kafkaPort;

    @Bean
    public KafkaDispatcher<PersonCreationEmailRequest> getOrderKafkaDispatcherPersonCreationEmailValidation() {
        return new KafkaDispatcher<>(kafkaPort);
    }

    @Bean
    public KafkaDispatcher<PasswordChangeEmailValidationEmailRequestKafkaMessage> getOrderKafkaDispatcherPasswordChangeEmailValidation() {
        return new KafkaDispatcher<>(kafkaPort);
    }

    @Bean
    public KafkaDispatcher<EmailChangeRequestEmailValidationEmailRequestKafkaMessage> getEmailChangeEmailValidationEmailRequestKafkaMessageKafkaDispatcher() {
        return new KafkaDispatcher<>(kafkaPort);
    }

    @Bean
    public KafkaDispatcher<EmailChangedEmailRequestKafkaMessage> getEmailChangedEmailRequestKafkaMessageKafkaDispatcher() {
        return new KafkaDispatcher<>(kafkaPort);
    }
}
