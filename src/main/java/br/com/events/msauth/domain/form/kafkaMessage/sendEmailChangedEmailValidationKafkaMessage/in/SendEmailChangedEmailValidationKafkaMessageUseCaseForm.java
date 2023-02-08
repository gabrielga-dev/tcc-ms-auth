package br.com.events.msauth.domain.form.kafkaMessage.sendEmailChangedEmailValidationKafkaMessage.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to contact the person that someone changed its email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class SendEmailChangedEmailValidationKafkaMessageUseCaseForm {

    private String personFirstName;
    private String personLastName;
    private String email;

}
