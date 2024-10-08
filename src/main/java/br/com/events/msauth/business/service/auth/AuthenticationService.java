package br.com.events.msauth.business.service.auth;

import br.com.events.msauth.adapters.repository.PersonRepository;
import br.com.events.msauth.business.exception.person.NoPersonWithGivenEmailFoundException;
import br.com.events.msauth.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class implements the {@link UserDetailsService} interface so it can load a person from the database by its
 * email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

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

    public Person getAuthenticatedPerson(){
        return repository.findByEmailAndActiveTrue(
            ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
        ).orElseThrow(
            NoPersonWithGivenEmailFoundException::new
        );
    }
}
