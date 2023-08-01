package br.com.events.msauth.clean.process.person.change_password._use_case;

import br.com.events.msauth.clean.domain.dto.person.change_passoword.ChangePasswordDTO;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.change_password.in.ChangePasswordForm;
import br.com.events.msauth.clean.process.person.change_password._use_case.interfaces.ChangePersonPasswordUseCase;
import br.com.events.msauth.clean.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.clean.process.person.change_password.validations.ChangePasswordValidationCaller;
import br.com.events.msauth.legacy.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.legacy.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.legacy.domain.repository.EmailValidationRepository;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
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

    private final ChangePasswordValidationCaller changePasswordValidationCaller;


    private final EmailValidationRepository emailValidationRepository;
    private final PersonRepository personRepository;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(String emailValidationUuid, ChangePasswordForm form) {
        this.validateData(emailValidationUuid, form);

        var emailValidation = emailValidationRepository.findByUuidAndValidatedIsFalse(emailValidationUuid)
                .orElseThrow(
                        EmailValidationNotFoundException::new
                );

        var person = personRepository.findById(emailValidation.getPerson().getUuid())
                .orElseThrow(NoPersonFoundByGivenUuidException::new);

        person.setPassword(passwordEncoder.encode(form.getPassword()));

        personRepository.save(person);

        var validateEmailValidationUseCaseForm = ValidateEmailValidationUseCaseForm
                .builder()
                .emailValidationUuid(emailValidationUuid)
                .emailValidationType(EmailValidationType.PASSWORD_CHANGE)
                .build();

        validateEmailValidationUseCase.execute(validateEmailValidationUseCaseForm);
    }

    private void validateData(String emailValidationUuid, ChangePasswordForm form) {
        var changePasswordData = ChangePasswordDTO
                .builder()
                .password(form.getPassword())
                .passwordRepeated(form.getPasswordRepeated())
                .emailValidationUuid(emailValidationUuid)
                .build();
        changePasswordValidationCaller.validate(changePasswordData);
    }
}
