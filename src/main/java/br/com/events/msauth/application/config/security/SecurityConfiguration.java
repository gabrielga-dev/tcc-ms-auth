package br.com.events.msauth.application.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.events.msauth.application.config.requestInterceptors.TokenInterceptor;
import br.com.events.msauth.application.service.AuthenticateServiceImpl;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.service.JwtTokenService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticateServiceImpl authService;

    private final JwtTokenService jwtTokenService;

    private final PersonRepository personRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers(HttpMethod.POST, "/v1/person").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/person/validate-email/{validationUuid}").permitAll()
            .antMatchers(HttpMethod.PATCH, "/v1/person/validate-email/{validationUuid}").permitAll()
            .antMatchers(HttpMethod.POST, "/v1/person/token").permitAll()
            .anyRequest().authenticated()
            .and().cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(
                new TokenInterceptor(jwtTokenService, personRepository), UsernamePasswordAuthenticationFilter.class
            );
    }
}
