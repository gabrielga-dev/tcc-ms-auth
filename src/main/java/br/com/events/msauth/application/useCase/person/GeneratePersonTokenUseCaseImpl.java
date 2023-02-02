package br.com.events.msauth.application.useCase.person;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.events.msauth.application.useCase.exception.person.InvalidCredentialsToGenerateJwtTokenException;
import br.com.events.msauth.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import br.com.events.msauth.domain.mapper.person.GeneratePersonTokenFormMapper;
import br.com.events.msauth.infrastructure.service.JwtTokenService;
import br.com.events.msauth.infrastructure.useCase.person.GeneratePersonTokenUseCase;
import lombok.RequiredArgsConstructor;

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
    public GeneratePersonTokenResult execute(final GeneratePersonTokenForm param) {
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
