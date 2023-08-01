package br.com.events.msauth.clean.domain.dto.kafka;

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
public class BaseEmailRequest {

    protected String to;

    protected Long templateId;
}
