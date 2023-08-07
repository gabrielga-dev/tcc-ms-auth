package br.com.events.msauth.process.person.create.validations.validations;

import br.com.events.msauth.domain.exception._process.person.create.PersonCreationEmailAvailableException;
import br.com.events.msauth.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.process.person.create.validations.CreatePersonValidation;
import br.com.events.msauth.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link CreatePersonValidation} and validate if the incoming email is available. If it
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
