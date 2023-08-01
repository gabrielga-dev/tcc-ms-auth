package br.com.events.msauth.clean.process.process.person.create.validation.validations;

import br.com.events.msauth.clean.domain.exception._process.person.create.PersonCreationCpfAvailableException;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.clean.process.process.person.create.validation.CreatePersonValidation;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.validation.person.create.PersonCreationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link PersonCreationValidation} and validate if the incoming CPF is available. If it
 * isn't, then throws a {@link PersonCreationCpfAvailableException}.
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PersonCreationCpfAvailableValidationImpl implements CreatePersonValidation {

    private final PersonRepository repository;

    @Override
    public Void process(CreatePersonForm toProcess) {
        if (repository.findByCpfAndActiveTrue(toProcess.getCpf()).isPresent()) {
            throw new PersonCreationCpfAvailableException();
        }
        return null;
    }
}
