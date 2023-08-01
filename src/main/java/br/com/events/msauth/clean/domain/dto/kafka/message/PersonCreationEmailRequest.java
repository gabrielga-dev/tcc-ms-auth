package br.com.events.msauth.clean.domain.dto.kafka.message;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PersonCreationEmailRequest extends BaseEmailRequest {

    private String personFirstName;
    private String personLastName;
    private String emailValidationUuid;
}
