package br.com.events.msauth.process.person.generate_token.service;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.process.person.generate_token.service.interfaces.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * This class has all needed methods to work with JWT tokens
 *
 * @author Gabriel Guimarães de Almeida
 */
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${application.name}")
    private String applicationName;

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * This method generates a new JWT token
     *
     * @param authentication {@link Authentication} object with the needed information for generate the token
     * @return {@link String} string containing the generated JWT token
     */
    @Override
    public String generateToken(final Authentication authentication) {
        var person = (Person) authentication.getPrincipal();
        var today = new Date();
        var expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
            .setIssuer(applicationName)
            .setSubject(person.getUuid())
            .setIssuedAt(today)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    /**
     * This method checks if the JWT token is valid
     *
     * @param token {@link String} string containing the JWT token
     * @return {@link Boolean} response for the validation
     */
    @Override
    public Boolean isValidToken(final String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method takes the person uuid from a JWT token
     *
     * @param token {@link String} JWT token
     * @return {@link String} person's uuid
     */
    @Override
    public String getPersonUuid(final String token) {
        return Jwts
            .parser()
            .setSigningKey(this.secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
