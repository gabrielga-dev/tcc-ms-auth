package br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage;

import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendPasswordChangeEmailValidationKafkaMessageUseCase.in.SendPasswordChangeEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to send a kafka message about password change
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface SendPasswordChangeEmailValidationKafkaMessageUseCase
    extends UseCaseBase<SendPasswordChangeEmailValidationKafkaMessageUseCaseForm, Void> {

}
