package br.com.events.msauth.adapters.dispatcher.config;

import br.com.events.msauth.adapters.dispatcher.KafkaDispatcher;
import br.com.events.msauth.domain.io.kafka_messages.person.create.PersonCreationEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.person.email_change.EmailChangeRequestEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.person.email_changed.EmailChangedEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.person.password_change.PasswordChangeRequestEmailRequest;
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
public class KafkaDispatcherConfig {

    @Value("${kafka.port}")
    private String kafkaPort;

    @Bean
    public KafkaDispatcher<PersonCreationEmailRequest> getOrderKafkaDispatcherPersonCreationEmailValidation() {
        return new KafkaDispatcher<>(kafkaPort);
    }

    @Bean
    public KafkaDispatcher<PasswordChangeRequestEmailRequest> getOrderKafkaDispatcherPasswordChangeEmailValidation() {
        return new KafkaDispatcher<>(kafkaPort);
    }

    @Bean
    public KafkaDispatcher<EmailChangeRequestEmailRequest> getEmailChangeEmailValidationEmailRequestKafkaMessageKafkaDispatcher() {
        return new KafkaDispatcher<>(kafkaPort);
    }

    @Bean
    public KafkaDispatcher<EmailChangedEmailRequest> getEmailChangedEmailRequestKafkaMessageKafkaDispatcher() {
        return new KafkaDispatcher<>(kafkaPort);
    }
}
