package br.com.events.msauth.infrastructure.service;

import org.springframework.security.core.Authentication;

/**
 * This interface dictates every needed method to work with JWT tokens
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface JwtTokenService {

    String generateToken(Authentication authentication);

    Boolean isValidToken(String token);

    String getPersonUuid(String token);

}
