package br.com.events.msauth.domain.io.kafka_messages.person.create;

import br.com.events.msauth.domain.io.kafka_messages.BaseEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.RawEmailRequest;
import br.com.events.msauth.domain.type.EmailTemplateIdType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCreationEmailRequest extends BaseEmailRequest {

    private final String personFirstName;
    private final String personLastName;
    private final String emailValidationUuid;

    public PersonCreationEmailRequest(RawEmailRequest emailRequest) {
        super(
                emailRequest.getKeyAndValues().get("email"),
                EmailTemplateIdType.PERSON_CREATION_EMAIL_VALIDATION.getId()
        );
        this.personFirstName = emailRequest.getKeyAndValues().get("personFirstName");
        this.personLastName = emailRequest.getKeyAndValues().get("personLastName");
        this.emailValidationUuid = emailRequest.getKeyAndValues().get("emailValidationUuid");
    }
}
