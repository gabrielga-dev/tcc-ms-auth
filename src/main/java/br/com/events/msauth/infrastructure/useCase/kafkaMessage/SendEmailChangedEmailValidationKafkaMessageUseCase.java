package br.com.events.msauth.infrastructure.useCase.kafkaMessage;

import br.com.events.msauth.domain.form.kafkaMessage.sendEmailChangedEmailValidationKafkaMessage.in.SendEmailChangedEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates what is needed to send a kafka message about the email has changed
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface SendEmailChangedEmailValidationKafkaMessageUseCase
    extends UseCaseBase<SendEmailChangedEmailValidationKafkaMessageUseCaseForm, Void> {

}
