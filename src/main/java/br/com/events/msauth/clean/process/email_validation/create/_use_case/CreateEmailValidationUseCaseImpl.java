package br.com.events.msauth.clean.process.email_validation.create._use_case;

import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces.CreateEmailValidationUseCase;
import br.com.events.msauth.legacy.application.useCase.exception.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.legacy.domain.repository.EmailValidationRepository;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEmailValidationUseCaseImpl implements CreateEmailValidationUseCase {

    private final EmailValidationRepository repository;
    private final PersonRepository personRepository;

    @Override
    public EmailValidation execute(String personUuid, EmailValidation emailValidation) {
        var person = personRepository.findById(personUuid)
                .orElseThrow(NoPersonFoundByGivenUuidException::new);

        emailValidation.setPerson(person);
        return repository.save(emailValidation);
    }
}
