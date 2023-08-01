package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.legacy.application.service.AuthenticationService;
import br.com.events.msauth.legacy.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.legacy.application.useCase.exception.person.NotAbleToUpdateOtherPersonInformationException;
import br.com.events.msauth.legacy.domain.form.person.update.in.UpdatePersonUseCaseForm;
import br.com.events.msauth.legacy.domain.form.person.update.out.UpdatePersonResult;
import br.com.events.msauth.legacy.domain.mapper.person.UpdatePersonUseCaseMapper;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.person.UpdatePersonUseCase;
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
