package br.com.events.msauth.application.validation.person.create;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.validation.person.create.exception.PersonCreationEmailAvailableException;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidation;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonCreationEmailAvailableValidation implements PersonCreationValidation {

    private final PersonRepository repository;

    @Override
    public void validate(final CreatePersonForm toValidate) {
        if (repository.findByEmailAndActiveTrue(toValidate.getEmail()).isPresent()) {
            throw new PersonCreationEmailAvailableException();
        }
    }
}
