package br.com.events.msauth.infrastructure.useCase.kafkaMessage;

import br.com.events.msauth.domain.form.kafkaMessage.sendPersonCreationEmailValidationKafkaMessage.in.SendPersonCreationEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link SendPersonCreationEmailValidationKafkaMessageUseCaseForm} to create a new
 * email validation for a person creation.
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface SendPersonCreationEmailValidationKafkaMessageUseCase
    extends UseCaseBase<SendPersonCreationEmailValidationKafkaMessageUseCaseForm, Void> {

}
