package br.com.events.msauth.legacy.infrastructure.useCase.person;

import br.com.events.msauth.legacy.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.legacy.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import br.com.events.msauth.legacy.infrastructure.UseCaseBase;

/**
 * This interface dictates that needs a {@link GeneratePersonTokenForm} to generate a person's JWT token.
 *
 * @author Gabriel Guimarães de Almeida
 */
public  interface GeneratePersonTokenUseCase extends UseCaseBase<GeneratePersonTokenForm, GeneratePersonTokenResult> {

}
