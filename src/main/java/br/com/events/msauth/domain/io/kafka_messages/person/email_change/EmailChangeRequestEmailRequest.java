package br.com.events.msauth.domain.io.kafka_messages.person.email_change;

import br.com.events.msauth.domain.io.kafka_messages.BaseEmailRequest;
import br.com.events.msauth.domain.io.kafka_messages.RawEmailRequest;
import br.com.events.msauth.domain.type.EmailTemplateIdType;
import lombok.Getter;

/**
 * This class holds the information that will be sent to ms-mailer to send a password change email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
public class EmailChangeRequestEmailRequest extends BaseEmailRequest {

    private final String personFirstName;
    private final String personLastName;
    private final String emailValidationUuid;

    public EmailChangeRequestEmailRequest(RawEmailRequest emailRequest) {
        super(emailRequest.getKeyAndValues().get("email"), EmailTemplateIdType.EMAIL_CHANGE_EMAIL_VALIDATION.getId());

        this.personFirstName = emailRequest.getKeyAndValues().get("personFirstName");
        this.personLastName = emailRequest.getKeyAndValues().get("personLastName");
        this.emailValidationUuid = emailRequest.getKeyAndValues().get("emailValidationUuid");
    }
}
