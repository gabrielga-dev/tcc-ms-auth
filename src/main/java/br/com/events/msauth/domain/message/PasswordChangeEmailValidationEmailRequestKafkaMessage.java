package br.com.events.msauth.domain.message;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PasswordChangeEmailValidationEmailRequestKafkaMessage extends EmailRequestKafkaMessageBase {

    private String personFirstName;
    private String personLastName;
    private String emailValidationUuid;
}
