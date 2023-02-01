package br.com.events.msauth.application.useCase.person;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.person.SetValidStatusOnPersonByEmailValidationsUseCase;
import lombok.RequiredArgsConstructor;

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
