package br.com.events.msauth.application.config.kafkaDispatcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.events.msauth.application.dispatcher.KafkaDispatcher;
import br.com.events.msauth.domain.message.PersonCreationEmailRequestMessage;
import lombok.extern.slf4j.Slf4j;

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
    public KafkaDispatcher<PersonCreationEmailRequestMessage> getOrderKafkaDispatcher() {
        return new KafkaDispatcher<>(kafkaPort);
    }
}
