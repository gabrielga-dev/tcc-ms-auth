package br.com.events.msauth.domain.form.kafkaMessage.sendPersonCreationEmailValidationKafkaMessage.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SendPersonCreationEmailValidationKafkaMessageUseCaseForm {

    private String personFirstName;
    private String personLastName;
    private String email;
    private String emailValidationUuid;
}
