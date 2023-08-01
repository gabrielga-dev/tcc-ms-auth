package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.legacy.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.legacy.domain.form.person.findByUuid.out.FindPersonByUuidUseCaseResult;
import br.com.events.msauth.legacy.domain.mapper.person.FindPersonByUuidUseCaseMapper;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.person.FindPersonByUuidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link FindPersonByUuidUseCase} interface to find a person by its uuid
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class FindPersonByUuidUseCaseImpl implements FindPersonByUuidUseCase {

    private final PersonRepository repository;

    @Override
    public FindPersonByUuidUseCaseResult execute(final String personUuid) {
        var personFound = repository.findById(personUuid)
            .orElseThrow(NoPersonFoundByGivenUuidException::new);

        return FindPersonByUuidUseCaseMapper.convertToResult(personFound);
    }
}
