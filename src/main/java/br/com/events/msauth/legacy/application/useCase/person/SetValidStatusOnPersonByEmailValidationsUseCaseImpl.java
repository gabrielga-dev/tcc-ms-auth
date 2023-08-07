package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.clean.domain.exception._process.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.person.SetValidStatusOnPersonByEmailValidationsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This interface checks if the person account is valid and then update it
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class SetValidStatusOnPersonByEmailValidationsUseCaseImpl implements
    SetValidStatusOnPersonByEmailValidationsUseCase {

    private final PersonRepository personRepository;

    @Override
    public Void execute(final String personUuid) {
        var personFound = personRepository.findById(personUuid)
            .orElseThrow(NoPersonFoundByGivenUuidException::new);

        var validStatus = personFound.getEmailConfirmations()
            .stream()
            .allMatch(validation -> validation.getValidated() && Objects.nonNull(validation.getValidationDate()));

        personFound.setActive(validStatus);

        personRepository.save(personFound);

        return null;
    }
}
