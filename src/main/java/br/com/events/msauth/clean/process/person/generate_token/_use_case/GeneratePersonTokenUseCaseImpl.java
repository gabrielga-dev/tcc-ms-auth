package br.com.events.msauth.clean.process.person.generate_token._use_case;

import br.com.events.msauth.clean.infrastructure.controller.entity.person.generate_token.in.GeneratePersonTokenForm;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.generate_token.out.GeneratePersonTokenResult;
import br.com.events.msauth.clean.process.person.generate_token._use_case.interfaces.GeneratePersonTokenUseCase;
import br.com.events.msauth.legacy.application.useCase.exception.person.InvalidCredentialsToGenerateJwtTokenException;
import br.com.events.msauth.legacy.domain.mapper.person.GeneratePersonTokenFormMapper;
import br.com.events.msauth.legacy.infrastructure.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * This class implements the {@link GeneratePersonTokenUseCase} interface to generate a person's JWT token
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class GeneratePersonTokenUseCaseImpl implements GeneratePersonTokenUseCase {

    private final JwtTokenService jwtTokenService;

    private final AuthenticationManager authenticationManager;

    @Override
    public GeneratePersonTokenResult execute(GeneratePersonTokenForm param) {
        try {
            var parsedForm = GeneratePersonTokenFormMapper.convertToUsernamePasswordAuthenticationToken(param);
            var authentication = authenticationManager.authenticate(parsedForm);
            var token = jwtTokenService.generateToken(authentication);
            return GeneratePersonTokenResult
                    .builder()
                    .token(token)
                    .type("Bearer")
                    .build();
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsToGenerateJwtTokenException();
        }
    }
}
