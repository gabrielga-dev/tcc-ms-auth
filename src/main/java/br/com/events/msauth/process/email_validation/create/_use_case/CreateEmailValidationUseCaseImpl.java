package br.com.events.msauth.process.email_validation.create._use_case;

import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.process.email_validation.create._use_case.interfaces.CreateEmailValidationUseCase;
import br.com.events.msauth.domain.exception._process.person.NoPersonFoundByGivenUuidException;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.domain.repository.PersonRepository;
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
