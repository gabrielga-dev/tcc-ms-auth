package br.com.events.msauth.application.useCase.emailValidation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.CreatePersonCreationEmailValidationUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the {@link CreatePersonCreationEmailValidationUseCase} interface to create a new email
 * validation
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreatePersonCreationEmailValidationUseCaseImpl implements CreatePersonCreationEmailValidationUseCase {

    private final EmailValidationRepository repository;
    private final PersonRepository personRepository;

    @Override
    public Void execute(final String userUuid) {
        var person = personRepository.findById(userUuid)
            .orElseThrow(NoPersonFoundByGivenUuidException::new);

        var toSave = new EmailValidation();

        toSave.setPerson(person);
        toSave.setType(EmailValidationType.PERSON_CREATION);
        toSave.setCreationDate(LocalDateTime.now());

        repository.save(toSave);

        return null;
    }
}
