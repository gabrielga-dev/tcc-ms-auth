package br.com.events.msauth.domain.dto.kafka.email_request;

import br.com.events.msauth.domain.dto.kafka.BaseEmailRequest;
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
