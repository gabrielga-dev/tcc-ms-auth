package br.com.events.msauth.process.person.change_email.validations.validations;

import br.com.events.msauth.domain.dto.person.change_email.ChangeEmailDTO;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.process.person.change_email.validations.ChangeEmailValidation;
import br.com.events.msauth.domain.exception._process.person.change_email.ChangePersonEmailEmailNotAvailableException;
import br.com.events.msauth.domain.repository.PersonRepository;
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
public class ChangeEmailEmailAvailableValidationImpl implements ChangeEmailValidation {

    private final PersonRepository personRepository;

    @Override
    public Void process(final ChangeEmailDTO toValidate) {
        var personOpt = personRepository.findByEmailAndActiveTrue(
            toValidate.getNewEmail()
        );

        if (personOpt.isPresent() && isNotWaitingForEmailValidation(personOpt.get())){
            throw new ChangePersonEmailEmailNotAvailableException();
        }
        return null;
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
