package br.com.events.msauth.clean.process.person.create.validations.validations;

import br.com.events.msauth.clean.domain.exception._process.person.create.PersonCreationEmailAvailableException;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.clean.process.person.create.validations.CreatePersonValidation;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.validation.person.create.PersonCreationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link PersonCreationValidation} and validate if the incoming email is available. If it
 * isn't, then throws a {@link PersonCreationEmailAvailableException}.
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PersonCreationEmailAvailableValidationImpl implements CreatePersonValidation {

    private final PersonRepository repository;

    @Override
    public Void process(CreatePersonForm toValidate) {
        if (repository.findByEmailAndActiveTrue(toValidate.getEmail()).isPresent()) {
            throw new PersonCreationEmailAvailableException();
        }
        return null;
    }
}
