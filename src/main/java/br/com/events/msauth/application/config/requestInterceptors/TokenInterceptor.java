package br.com.events.msauth.application.config.requestInterceptors;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.events.msauth.application.service.exception.NoPersonWithJwtTokenUuidFoundException;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.service.JwtTokenService;
import lombok.RequiredArgsConstructor;

/**
 * This class intercept every request and check if there is a Authorization header containing a JWT and validate it
 *
 * @author Gabriel Guimarães de Almeida
 */
@Configuration
@RequiredArgsConstructor
public class TokenInterceptor extends OncePerRequestFilter {

    private final JwtTokenService tokenService;

    private final PersonRepository personRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String token = retrivetoken(request);
        if (tokenService.isValidToken(token)) {
            authenticateUser(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        var userId = tokenService.getPersonUuid(token);
        var user = personRepository.findById(userId)
            .orElseThrow(
                NoPersonWithJwtTokenUuidFoundException::new
            );
        var authentication = new UsernamePasswordAuthenticationToken(
            user,
            null,
            user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String retrivetoken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }
}
