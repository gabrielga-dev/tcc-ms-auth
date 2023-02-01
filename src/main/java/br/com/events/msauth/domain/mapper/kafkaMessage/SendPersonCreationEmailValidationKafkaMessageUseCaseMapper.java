package br.com.events.msauth.domain.mapper.kafkaMessage;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.kafkaMessage.sendPersonCreationEmailValidationKafkaMessage.in.SendPersonCreationEmailValidationKafkaMessageUseCaseForm;
import br.com.events.msauth.domain.form.kafkaMessage.type.EmailTemplateIdType;
import br.com.events.msauth.domain.message.PersonCreationEmailValidationEmailRequestKafkaMessage;
import br.com.events.msauth.infrastructure.useCase.kafkaMessage.SendPersonCreationEmailValidationKafkaMessageUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at
 * {@link SendPersonCreationEmailValidationKafkaMessageUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SendPersonCreationEmailValidationKafkaMessageUseCaseMapper {

    /**
     * This method maps a {@link Person} object into a {@link SendPersonCreationEmailValidationKafkaMessageUseCaseForm}
     * object
     *
     * @param person {@link Person} object with the data to be mapped
     * @return {@link SendPersonCreationEmailValidationKafkaMessageUseCaseForm} object with the mapped data
     */
    public static SendPersonCreationEmailValidationKafkaMessageUseCaseForm convertToForm(
        Person person, String emailValidationUuid
    ) {
        return SendPersonCreationEmailValidationKafkaMessageUseCaseForm
            .builder()
            .personFirstName(person.getFirstName())
            .personLastName(person.getLastName())
            .email(person.getEmail())
            .emailValidationUuid(emailValidationUuid)
            .build();
    }

    /**
     * This method maps a {@link SendPersonCreationEmailValidationKafkaMessageUseCaseForm} object into a
     * {@link PersonCreationEmailValidationEmailRequestKafkaMessage} object
     *
     * @param form {@link SendPersonCreationEmailValidationKafkaMessageUseCaseForm} object with the data to be mapped
     * @return {@link PersonCreationEmailValidationEmailRequestKafkaMessage} object with the mapped data
     */
    public static PersonCreationEmailValidationEmailRequestKafkaMessage convertToKafkaMessage(
        SendPersonCreationEmailValidationKafkaMessageUseCaseForm form
    ) {
        return PersonCreationEmailValidationEmailRequestKafkaMessage
            .builder()
            .templateId(EmailTemplateIdType.PERSON_CREATION_EMAIL_VALIDATION.getId())
            .to(form.getEmail())
            .personFirstName(form.getPersonFirstName())
            .personLastName(form.getPersonLastName())
            .emailValidationUuid(form.getEmailValidationUuid())
            .build();
    }
}
