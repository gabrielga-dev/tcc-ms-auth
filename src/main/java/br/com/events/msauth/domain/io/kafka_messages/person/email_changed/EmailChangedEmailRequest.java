package br.com.events.msauth.domain.io.kafka_messages.person.email_changed;

import br.com.events.msauth.domain.io.kafka_messages.BaseEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.RawEmailRequest;
import br.com.events.msauth.domain.type.EmailTemplateIdType;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds the information that will be sent to ms-mailer to send a password change email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
public class EmailChangedEmailRequest extends BaseEmailRequest {

    private final String personFirstName;
    private final String personLastName;

    public EmailChangedEmailRequest(RawEmailRequest request) {
        super(request.getKeyAndValues().get("email"), EmailTemplateIdType.EMAIL_CHANGED.getId());
        this.personFirstName = request.getKeyAndValues().get("personFirstName");
        this.personLastName = request.getKeyAndValues().get("personLastName");
    }
}
