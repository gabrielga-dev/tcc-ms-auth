package br.com.events.msauth.application.useCase.person;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.service.AuthenticationService;
import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.application.useCase.exception.person.NotAbleToUpdateOtherPersonInformationException;
import br.com.events.msauth.domain.form.person.update.in.UpdatePersonUseCaseForm;
import br.com.events.msauth.domain.form.person.update.out.UpdatePersonResult;
import br.com.events.msauth.domain.mapper.person.UpdatePersonUseCaseMapper;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.person.UpdatePersonUseCase;
import lombok.RequiredArgsConstructor;

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
    public UpdatePersonResult execute(final UpdatePersonUseCaseForm param) {
        var authenticatedPersonUuid = authenticationService.getAuthenticatedPerson().getUuid();
        if(!Objects.equals(authenticatedPersonUuid, param.getPersonUuid())){
            throw new NotAbleToUpdateOtherPersonInformationException();
        }

        var person = personRepository.findByUuidAndActiveTrue(param.getPersonUuid())
            .orElseThrow(NoPersonFoundByGivenUuidException::new);

        person.setFirstName(param.getFirstName());
        person.setLastName(param.getLastName());

        var updatedPerson = personRepository.save(person);

        return UpdatePersonUseCaseMapper.convertToResult(updatedPerson);
    }
}
