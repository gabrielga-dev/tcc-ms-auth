package br.com.events.msauth.clean.process.person.update._use_case;

import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.update.in.UpdatePersonForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.update.out.UpdatePersonResult;
import br.com.events.msauth.clean.process.person.update._use_case.interfaces.UpdatePersonUseCase;
import br.com.events.msauth.clean.process.authentication.service.AuthenticationService;
import br.com.events.msauth.clean.domain.exception._process.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.clean.domain.exception._process.person.update.NotAbleToUpdateOtherPersonInformationException;
import br.com.events.msauth.clean.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class update a person with the incoming new data
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class UpdatePersonUseCaseImpl implements UpdatePersonUseCase {

    private final AuthenticationService authenticationService;
    private final PersonRepository personRepository;

    @Override
    public UpdatePersonResult execute(String personUuid, UpdatePersonForm param) {
        this.validateAuthenticatedPerson(personUuid);

        var person = personRepository.findByUuidAndActiveTrue(personUuid)
            .orElseThrow(NoPersonFoundByGivenUuidException::new);

        person.setFirstName(param.getFirstName());
        person.setLastName(param.getLastName());

        var updatedPerson = personRepository.save(person);

        return this.convertToResult(updatedPerson);
    }

    private void validateAuthenticatedPerson(String personUuid) {
        var authenticatedPersonUuid = authenticationService.getAuthenticatedPerson().getUuid();
        if(!Objects.equals(authenticatedPersonUuid, personUuid)){
            throw new NotAbleToUpdateOtherPersonInformationException();
        }
    }

    private UpdatePersonResult convertToResult(Person person) {
        return UpdatePersonResult
                .builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
    }
}
