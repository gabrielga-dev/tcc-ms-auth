package br.com.events.msauth.infrastructure.useCase.kafkaMessage;

import br.com.events.msauth.domain.form.kafkaMessage.sendEmailChangeRequestEmailValidationKafkaMessage.in.SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to send a kafka message about email change
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface SendEmailChangeRequestEmailValidationKafkaMessageUseCase
    extends UseCaseBase<SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm, Void> {

}
