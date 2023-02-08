package br.com.events.msauth.domain.message;

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
public class EmailChangedEmailRequestKafkaMessage extends EmailRequestKafkaMessageBase {

    private String personFirstName;
    private String personLastName;
}
