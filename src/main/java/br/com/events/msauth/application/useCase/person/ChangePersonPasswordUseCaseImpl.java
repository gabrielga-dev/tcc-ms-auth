package br.com.events.msauth.application.useCase.person;

import br.com.events.msauth.application.useCase.exception.emailValidation.EmailValidationNotFoundException;
import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.person.ChangePersonPasswordUseCase;
import br.com.events.msauth.infrastructure.validation.person.changePassword.ChangePersonPasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class changes a person password
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangePersonPasswordUseCaseImpl implements ChangePersonPasswordUseCase {

    private final ChangePersonPasswordValidator changePersonPasswordValidator;
    private final EmailValidationRepository emailValidationRepository;
    private final PersonRepository personRepository;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Void execute(final ChangePersonPasswordUseCaseForm form) {
        changePersonPasswordValidator.validate(form);

        var emailValidation = emailValidationRepository.findByUuidAndValidatedIsFalse(form.getEmailValidationUuid())
            .orElseThrow(
                EmailValidationNotFoundException::new
            );

        var person = personRepository.findById(emailValidation.getPerson().getUuid())
            .orElseThrow(NoPersonFoundByGivenUuidException::new);

        person.setPassword(passwordEncoder.encode(form.getPassword()));

        personRepository.save(person);

        var validateEmailValidationUseCaseForm = ValidateEmailValidationUseCaseForm
            .builder()
            .emailValidationUuid(form.getEmailValidationUuid())
            .emailValidationType(EmailValidationType.PASSWORD_CHANGE)
            .build();

        validateEmailValidationUseCase.execute(validateEmailValidationUseCaseForm);

        return null;
    }
}
