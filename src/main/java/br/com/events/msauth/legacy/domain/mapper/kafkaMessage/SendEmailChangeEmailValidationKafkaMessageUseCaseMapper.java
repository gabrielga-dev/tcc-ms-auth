package br.com.events.msauth.legacy.domain.mapper.kafkaMessage;

import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendEmailChangeRequestEmailValidationKafkaMessage.in.SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.clean.domain.type.EmailTemplateIdType;
import br.com.events.msauth.clean.domain.dto.kafka.EmailChangeRequestEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage.SendEmailChangeRequestEmailValidationKafkaMessageUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every mapping method needed at {@link SendEmailChangeRequestEmailValidationKafkaMessageUseCase}
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SendEmailChangeEmailValidationKafkaMessageUseCaseMapper {

    /**
     * This method maps a {@link Person} object into a {@link SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm}
     * object
     *
     * @param person {@link Person} object with the data to be mapped
     * @return {@link SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm} object with the mapped data
     */
    public static SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm convertToForm(
        Person person, String emailValidationUuid
    ) {
        return SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm
            .builder()
            .personFirstName(person.getFirstName())
            .personLastName(person.getLastName())
            .email(person.getEmail())
            .emailValidationUuid(emailValidationUuid)
            .build();
    }

    /**
     * This method map a {@link SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm} into a
     * {@link EmailChangeRequestEmailValidationEmailRequestKafkaMessage} object
     *
     * @param form {@link SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm} object with the data that will be
     * mapped
     * @return {@link EmailChangeRequestEmailValidationEmailRequestKafkaMessage} object with the mapped data
     */
    public static EmailChangeRequestEmailValidationEmailRequestKafkaMessage convertToKafkaMessage(
        SendEmailChangeRequestEmailValidationKafkaMessageUseCaseForm form
    ) {
        return EmailChangeRequestEmailValidationEmailRequestKafkaMessage
            .builder()
            .templateId(EmailTemplateIdType.EMAIL_CHANGE_EMAIL_VALIDATION.getId())
            .to(form.getEmail())
            .personFirstName(form.getPersonFirstName())
            .personLastName(form.getPersonLastName())
            .emailValidationUuid(form.getEmailValidationUuid())
            .build();
    }
}
