package br.com.events.msauth.domain.form.kafkaMessage.sendPasswordChangeEmailValidationKafkaMessageUseCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SendPasswordChangeEmailValidationKafkaMessageUseCaseForm {

    private String personFirstName;
    private String personLastName;
    private String email;
    private String emailValidationUuid;
}
