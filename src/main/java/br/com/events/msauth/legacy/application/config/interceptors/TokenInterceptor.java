package br.com.events.msauth.legacy.application.config.interceptors;

import br.com.events.msauth.legacy.application.service.exception.NoPersonWithJwtTokenUuidFoundException;
import br.com.events.msauth.legacy.domain.repository.PersonRepository;
import br.com.events.msauth.legacy.infrastructure.service.JwtTokenService;
import br.com.events.msauth.legacy.util.FilterExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

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
    private final FilterExceptionUtil filterExceptionUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        var token = request.getHeader("Authorization");

        if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = extractToken(token);

        if(tokenService.isValidToken(token)){
            authenticateUser(token);
            filterChain.doFilter(request, response);
            return;
        }
        filterExceptionUtil.setResponseError(response, new NoPersonWithJwtTokenUuidFoundException());
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

    private String extractToken(String token) {
        if (ObjectUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }
}
