package br.com.events.msauth.application.useCase.person;

import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.domain.form.person.findByUuid.out.FindPersonByUuidUseCaseResult;
import br.com.events.msauth.domain.mapper.person.FindPersonByUuidUseCaseMapper;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.person.FindPersonByUuidUseCase;
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
