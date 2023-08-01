package br.com.events.msauth.clean.process.person.generate_token._use_case.interfaces;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.generate_token.in.GeneratePersonTokenForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.generate_token.out.GeneratePersonTokenResult;

public interface GeneratePersonTokenUseCase {

    GeneratePersonTokenResult execute(GeneratePersonTokenForm form);
}
