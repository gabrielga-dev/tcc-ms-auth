package br.com.events.msauth.legacy.domain.mapper.kafkaMessage;


import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.legacy.domain.form.kafkaMessage.sendPasswordChangeEmailValidationKafkaMessageUseCase.in.SendPasswordChangeEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.clean.domain.type.EmailTemplateIdType;
import br.com.events.msauth.clean.domain.dto.kafka.PasswordChangeEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage.SendPasswordChangeEmailValidationKafkaMessageUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at
 * {@link SendPasswordChangeEmailValidationKafkaMessageUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SendPasswordChangeEmailValidationKafkaMessageUseCaseMapper {

    /**
     * This method maps a {@link Person} object into a {@link SendPasswordChangeEmailValidationKafkaMessageUseCaseForm}
     * object
     *
     * @param person {@link Person} object with the data to be mapped
     * @return {@link SendPasswordChangeEmailValidationKafkaMessageUseCaseForm} object with the mapped data
     */
    public static SendPasswordChangeEmailValidationKafkaMessageUseCaseForm convertToForm(
        Person person, String emailValidationUuid
    ) {
        return SendPasswordChangeEmailValidationKafkaMessageUseCaseForm
            .builder()
            .personFirstName(person.getFirstName())
            .personLastName(person.getLastName())
            .email(person.getEmail())
            .emailValidationUuid(emailValidationUuid)
            .build();
    }

    public static PasswordChangeEmailValidationEmailRequestKafkaMessage convertToKafkaMessage(
        SendPasswordChangeEmailValidationKafkaMessageUseCaseForm form
    ){
        return PasswordChangeEmailValidationEmailRequestKafkaMessage
            .builder()
            .templateId(EmailTemplateIdType.PASSWORD_CHANGE_EMAIL_VALIDATION.getId())
            .to(form.getEmail())
            .personFirstName(form.getPersonFirstName())
            .personLastName(form.getPersonLastName())
            .emailValidationUuid(form.getEmailValidationUuid())
            .build();
    }
}
