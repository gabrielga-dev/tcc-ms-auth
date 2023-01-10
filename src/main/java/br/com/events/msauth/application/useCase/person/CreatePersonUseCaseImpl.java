package br.com.events.msauth.application.useCase.person;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonResult;
import br.com.events.msauth.domain.mapper.PersonMapper;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.useCase.person.CreatePersonUseCase;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidator;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePersonUseCaseImpl implements CreatePersonUseCase {

    private final PersonRepository repository;
    private final PersonCreationValidator validator;

    @Override
    public CreatePersonResult execute(final CreatePersonForm param) {

        validator.validate(param);

        var toSave = PersonMapper.convertToEntity(param);
        toSave.setCreationDate(LocalDateTime.now());

        var saved = repository.save(toSave);

        return PersonMapper.convertToResult(saved);
    }
}
