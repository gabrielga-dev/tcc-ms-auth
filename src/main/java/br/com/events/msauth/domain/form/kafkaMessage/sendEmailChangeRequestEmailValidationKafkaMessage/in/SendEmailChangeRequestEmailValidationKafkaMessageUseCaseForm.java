package br.com.events.msauth.domain.form.kafkaMessage.sendEmailChangeRequestEmailValidationKafkaMessage.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to contact the person that someone is trying to change its email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm {

    private String personFirstName;
    private String personLastName;
    private String email;
    private String emailValidationUuid;
}
