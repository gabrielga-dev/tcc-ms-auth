package br.com.events.msauth.application.useCase.emailValidation.personCreation;

import br.com.events.msauth.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.personCreation.DeleteAllPastPersonCreationEmailValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * This class deletes all past person's person creation email validation
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class DeleteAllPastPersonCreationEmailValidationUseCaseImpl
    implements DeleteAllPastPersonCreationEmailValidationUseCase {

    private final EmailValidationRepository emailValidationRepository;
    private final PersonRepository personRepository;

    @Override
    public Void execute(final String userUuid) {
        var person = personRepository.findById(userUuid)
            .orElseThrow(NoPersonFoundByGivenUuidException::new);

        var emailValidationsToDelete = person.getEmailConfirmations()
            .stream()
            .filter(validation -> EmailValidationType.PERSON_CREATION.equals(validation.getType()))
            .collect(Collectors.toList());

        emailValidationRepository.deleteAll(emailValidationsToDelete);

        return null;
    }
}
