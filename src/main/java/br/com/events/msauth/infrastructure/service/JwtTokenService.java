package br.com.events.msauth.infrastructure.service;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {

    String generateToken(Authentication authentication);

    Boolean isValidToken(String token);

    String getPersonUuid(String token);

}
