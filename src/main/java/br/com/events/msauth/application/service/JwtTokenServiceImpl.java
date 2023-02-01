package br.com.events.msauth.application.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.infrastructure.service.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${application.name}")
    private String applicationName;

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateToken(final Authentication authentication) {
        var person = (Person) authentication.getPrincipal();
        var today = new Date();
        var expirationDate = new Date(today.getTime()+ Long.parseLong(expiration));

        return Jwts.builder()
            .setIssuer(applicationName)
            .setSubject(person.getUuid())
            .setIssuedAt(today)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    @Override
    public Boolean isValidToken(final String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

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
