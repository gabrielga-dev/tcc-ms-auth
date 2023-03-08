package br.com.events.msauth.application.validation.person.create;

import br.com.events.msauth.application.validation.person.create.exception.PersonCreationCpfAvailableException;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidation;
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
public class PersonCreationCpfAvailableValidation implements PersonCreationValidation {

    private final PersonRepository repository;

    @Override
    public void validate(final CreatePersonUseCaseForm toValidate) {
        if (repository.findByCpfAndActiveTrue(toValidate.getCpf()).isPresent()) {
            throw new PersonCreationCpfAvailableException();
        }
    }
}
