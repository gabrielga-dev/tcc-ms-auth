package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.legacy.application.useCase.exception.emailValidation.EmailValidationNotFoundException;
import br.com.events.msauth.legacy.application.useCase.exception.person.NoPersonFoundByGivenEmailException;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.legacy.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangeEmailRequestUseCaseForm;
import br.com.events.msauth.legacy.domain.mapper.kafkaMessage.SendEmailChangedEmailValidationKafkaMessageUseCaseMapper;
import br.com.events.msauth.legacy.domain.repository.EmailValidationRepository;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.kafkaMessage.SendEmailChangedEmailValidationKafkaMessageUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.ChangePersonEmailUseCase;
import br.com.events.msauth.legacy.infrastructure.validation.person.changeEmail.ChangePersonEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements {@link ChangePersonEmailUseCase} to change a person email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangePersonEmailUseCaseImpl implements ChangePersonEmailUseCase {

    private final ChangePersonEmailValidator changePersonEmailValidator;
    private final EmailValidationRepository emailValidationRepository;
    private final PersonRepository personRepository;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;
    private final SendEmailChangedEmailValidationKafkaMessageUseCase sendEmailChangedEmailValidationKafkaMessageUseCase;

    @Override
    public Void execute(final ChangeEmailRequestUseCaseForm param) {
        changePersonEmailValidator.validate(param);

        var emailValidation = emailValidationRepository.findByUuidAndValidatedIsFalse(param.getEmailValidationUuid())
            .orElseThrow(
                EmailValidationNotFoundException::new
            );

        var person = personRepository.findByUuidAndActiveTrue(emailValidation.getPerson().getUuid())
            .orElseThrow(
                NoPersonFoundByGivenEmailException::new
            );

        person.setEmail(param.getNewEmail());
        personRepository.save(person);

        var validationForm = ValidateEmailValidationUseCaseForm
            .builder()
            .emailValidationType(EmailValidationType.EMAIL_CHANGE)
            .emailValidationUuid(param.getEmailValidationUuid())
            .build();

        validateEmailValidationUseCase.execute(validationForm);

        var kafkaUseCaseForm = SendEmailChangedEmailValidationKafkaMessageUseCaseMapper.convertToForm(person);
        sendEmailChangedEmailValidationKafkaMessageUseCase.execute(kafkaUseCaseForm);

        return null;
    }
}
