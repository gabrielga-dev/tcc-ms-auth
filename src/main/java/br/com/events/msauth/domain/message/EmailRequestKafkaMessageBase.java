package br.com.events.msauth.domain.message;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * This class holds every needed information that comes from kafka queue
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@SuperBuilder
public class EmailRequestKafkaMessageBase {

    protected String to;

    protected String subject;

    protected Long templateId;
}
