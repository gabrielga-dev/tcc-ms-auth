package br.com.events.msauth.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.events.msauth.application.service.exception.NoPersonWithGivenEmailFoundException;
import br.com.events.msauth.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the {@link UserDetailsService} interface so it can load a person from the database by its
 * email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements UserDetailsService {

    private final PersonRepository repository;

    /**
     * This method find a person from the database by its email and if its is active
     *
     * @param username {@link String} person's email
     * @return {@link UserDetails} person's data
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return repository.findByEmailAndActiveTrue(username)
            .orElseThrow(
                NoPersonWithGivenEmailFoundException::new
            );
    }
}
