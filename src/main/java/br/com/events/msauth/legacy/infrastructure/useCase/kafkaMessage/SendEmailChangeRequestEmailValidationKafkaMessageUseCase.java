package br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage;

import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendEmailChangeRequestEmailValidationKafkaMessage.in.SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to send a kafka message about email change
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface SendEmailChangeRequestEmailValidationKafkaMessageUseCase
    extends UseCaseBase<SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm, Void> {

}
