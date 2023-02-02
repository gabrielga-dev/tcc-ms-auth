package br.com.events.msauth.infrastructure.useCase.person;

import br.com.events.msauth.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import br.com.events.msauth.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link GeneratePersonTokenForm} to generate a person's JWT token.
 *
 * @author Gabriel Guimarães de Almeida
 */
public  interface GeneratePersonTokenUseCase extends UseCaseBase<GeneratePersonTokenForm, GeneratePersonTokenResult> {

}
