package br.com.events.msauth.domain.form.kafkaMessage.sendPasswordChangeEmailValidationKafkaMessageUseCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to send to ms-mailer to send a password change request email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class SendPasswordChangeEmailValidationKafkaMessageUseCaseForm {

    private String personFirstName;
    private String personLastName;
    private String email;
    private String emailValidationUuid;
}
