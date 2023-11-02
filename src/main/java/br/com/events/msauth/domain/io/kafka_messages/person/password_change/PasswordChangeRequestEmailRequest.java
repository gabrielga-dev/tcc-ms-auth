package br.com.events.msauth.domain.io.kafka_messages.person.password_change;

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
public class PasswordChangeRequestEmailRequest extends BaseEmailRequest {

    private final String personFirstName;
    private final String personLastName;
    private final String emailValidationUuid;

    public PasswordChangeRequestEmailRequest(RawEmailRequest emailRequest) {
        super(
                emailRequest.getKeyAndValues().get("email"),
                EmailTemplateIdType.PASSWORD_CHANGE_EMAIL_VALIDATION.getId()
        );
        this.personFirstName = emailRequest.getKeyAndValues().get("personFirstName");
        this.personLastName = emailRequest.getKeyAndValues().get("personLastName");
        this.emailValidationUuid = emailRequest.getKeyAndValues().get("emailValidationUuid");
    }
}
