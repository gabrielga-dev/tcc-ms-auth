package br.com.events.msauth.process.person.set_person_as_active._use_case;

import br.com.events.msauth.domain.exception._process.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.process.person.set_person_as_active._use_case.interfaces.SetPersonAsActiveUseCase;
import br.com.events.msauth.domain.repository.PersonRepository;
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
public class SetPersonAsActiveUseCaseImpl implements SetPersonAsActiveUseCase {

    private final PersonRepository personRepository;

    @Override
    public void execute(final String personUuid) {
        var personFound = personRepository.findById(personUuid)
                .orElseThrow(NoPersonFoundByGivenUuidException::new);

        var validStatus = personFound.getEmailConfirmations()
                .stream()
                .allMatch(validation -> validation.getValidated() && Objects.nonNull(validation.getValidationDate()));

        personFound.setActive(validStatus);

        personRepository.save(personFound);
    }
}
