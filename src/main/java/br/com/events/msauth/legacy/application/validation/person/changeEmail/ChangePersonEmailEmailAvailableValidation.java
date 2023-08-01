package br.com.events.msauth.legacy.application.validation.person.changeEmail;

import br.com.events.msauth.legacy.application.validation.person.changeEmail.exception.ChangePersonEmailEmailNotAvailableException;
import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangeEmailRequestUseCaseForm;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.validation.person.changeEmail.ChangePersonEmailValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class checks if the incoming new email is available
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangePersonEmailEmailAvailableValidation implements ChangePersonEmailValidation {

    private final PersonRepository personRepository;

    @Override
    public void validate(final ChangeEmailRequestUseCaseForm toValidate) {
        var personOpt = personRepository.findByEmailAndActiveTrue(
            toValidate.getNewEmail()
        );

        if (personOpt.isPresent() && isNotWaitingForEmailValidation(personOpt.get())){
            throw new ChangePersonEmailEmailNotAvailableException();
        }
    }

    private boolean isNotWaitingForEmailValidation(final Person person) {
        return person.getEmailConfirmations()
            .stream()
            .noneMatch(
                emailValidation -> Objects.equals(EmailValidationType.PERSON_CREATION, emailValidation.getType())
                && emailValidation.getValidated()
            );
    }
}
