package br.com.events.msauth.legacy.domain.mapper.kafkaMessage;

import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendEmailChangedEmailValidationKafkaMessage.in.SendEmailChangedEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.clean.domain.type.EmailTemplateIdType;
import br.com.events.msauth.clean.domain.dto.kafka.email_request.EmailChangedEmailRequestKafkaMessage;
import br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage.SendEmailChangedEmailValidationKafkaMessageUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every mapping method needed at {@link SendEmailChangedEmailValidationKafkaMessageUseCase}
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SendEmailChangedEmailValidationKafkaMessageUseCaseMapper {

    /**
     * This method maps a {@link SendEmailChangedEmailValidationKafkaMessageUseCaseForm} object into a
     * {@link EmailChangedEmailRequestKafkaMessage} object
     *
     * @param toConvert {@link SendEmailChangedEmailValidationKafkaMessageUseCaseForm} object with the data to be
     * mapped
     * @return {@link EmailChangedEmailRequestKafkaMessage} object with the mapped data
     */
    public static EmailChangedEmailRequestKafkaMessage convertToKafkaMessage(
        SendEmailChangedEmailValidationKafkaMessageUseCaseForm toConvert
    ) {
        return EmailChangedEmailRequestKafkaMessage
            .builder()
            .templateId(EmailTemplateIdType.EMAIL_CHANGE_EMAIL_VALIDATION.getId())
            .personFirstName(toConvert.getPersonFirstName())
            .personLastName(toConvert.getPersonLastName())
            .to(toConvert.getEmail())
            .build();
    }

    /**
     * This method maps a {@link Person} object into a {@link SendEmailChangedEmailValidationKafkaMessageUseCaseForm}
     * object
     *
     * @param person {@link Person} object with the data to be mapped
     * @return {@link SendEmailChangedEmailValidationKafkaMessageUseCaseForm} object with the mapped data
     */
    public static SendEmailChangedEmailValidationKafkaMessageUseCaseForm convertToForm(final Person person) {
        return SendEmailChangedEmailValidationKafkaMessageUseCaseForm
            .builder()
            .personFirstName(person.getFirstName())
            .personLastName(person.getLastName())
            .email(person.getEmail())
            .build();
    }
}
