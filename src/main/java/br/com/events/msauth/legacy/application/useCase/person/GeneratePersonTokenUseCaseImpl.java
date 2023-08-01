package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.legacy.application.useCase.exception.person.InvalidCredentialsToGenerateJwtTokenException;
import br.com.events.msauth.legacy.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import br.com.events.msauth.legacy.domain.form.person.generateToken.out.GeneratePersonTokenResult;
import br.com.events.msauth.legacy.domain.mapper.person.GeneratePersonTokenFormMapper;
import br.com.events.msauth.legacy.infrastructure.service.JwtTokenService;
import br.com.events.msauth.legacy.infrastructure.useCase.person.GeneratePersonTokenUseCase;
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
