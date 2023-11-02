package br.com.events.msauth.domain.io.kafka_messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class holds every needed information that comes from kafka queue
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BaseEmailRequest {

    protected final String to;

    protected final Long templateId;
}
