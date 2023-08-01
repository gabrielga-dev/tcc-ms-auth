package br.com.events.msauth.clean.process.person.change_password._use_case;

import br.com.events.msauth.clean.domain.dto.email_validation.validate.in.ValidateEmailValidationDTO;
import br.com.events.msauth.clean.domain.dto.person.change_passoword.ValidateChangePasswordDTO;
import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.clean.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.change_password.in.ChangePasswordForm;
import br.com.events.msauth.clean.process.email_validation.find_by_uuid._use_case.interfaces.FindEmailValidationByUuidUseCase;
import br.com.events.msauth.clean.process.email_validation.validate._use_case.ValidateEmailValidationUseCase;
import br.com.events.msauth.clean.process.person.change_password._use_case.interfaces.ChangePersonPasswordUseCase;
import br.com.events.msauth.clean.process.person.change_password.validations.ChangePasswordValidationCaller;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
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
    private final FindEmailValidationByUuidUseCase findEmailValidationByUuidUseCase;


    private final PersonRepository personRepository;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(String emailValidationUuid, ChangePasswordForm form) {
        this.validateData(emailValidationUuid, form);

        var person = this.getEmailValidationPerson(emailValidationUuid);
        this.setEncryptedPasswordToPerson(person, form.getPassword());

        this.sendPasswordChangeEmail(emailValidationUuid);
    }

    private void validateData(String emailValidationUuid, ChangePasswordForm form) {
        var changePasswordData = ValidateChangePasswordDTO
                .builder()
                .password(form.getPassword())
                .passwordRepeated(form.getPasswordRepeated())
                .emailValidationUuid(emailValidationUuid)
                .build();
        changePasswordValidationCaller.validate(changePasswordData);
    }

    private Person getEmailValidationPerson(String emailValidationUuid) {
        var emailValidation = findEmailValidationByUuidUseCase.execute(emailValidationUuid);
        if (emailValidation.getValidated()) {
            throw new EmailValidationNotFoundException();
        }
        return emailValidation.getPerson();
    }

    private void setEncryptedPasswordToPerson(Person person, String password) {
        person.setPassword(passwordEncoder.encode(password));
        personRepository.save(person);
    }

    private void sendPasswordChangeEmail(String emailValidationUuid) {
        var validateEmailValidationUseCaseForm = ValidateEmailValidationDTO
                .builder()
                .emailValidationUuid(emailValidationUuid)
                .emailValidationType(EmailValidationType.PASSWORD_CHANGE)
                .build();

        validateEmailValidationUseCase.execute(validateEmailValidationUseCaseForm);
    }
}
