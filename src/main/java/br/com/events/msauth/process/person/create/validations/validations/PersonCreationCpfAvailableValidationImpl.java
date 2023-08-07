package br.com.events.msauth.process.person.create.validations.validations;

import br.com.events.msauth.domain.exception._process.person.create.PersonCreationCpfAvailableException;
import br.com.events.msauth.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.process.person.create.validations.CreatePersonValidation;
import br.com.events.msauth.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link CreatePersonValidation} and validate if the incoming CPF is available. If it
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
