package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonResult;
import br.com.events.msauth.infrastructure.UseCaseBase;

public interface CreatePersonUseCase extends UseCaseBase<CreatePersonForm, CreatePersonResult> {

}
