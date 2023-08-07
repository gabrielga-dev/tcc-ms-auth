package br.com.events.msauth.clean.domain.dto.kafka.email_request;

import br.com.events.msauth.clean.domain.dto.kafka.BaseEmailRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * This class holds the information that will be sent to ms-mailer to send a password change email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@SuperBuilder
public class EmailChangeRequestEmailValidationEmailRequestKafkaMessage extends BaseEmailRequest {

    private String personFirstName;
    private String personLastName;
    private String emailValidationUuid;
}
