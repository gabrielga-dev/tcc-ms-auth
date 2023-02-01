package br.com.events.msauth.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.events.msauth.application.service.exception.NoPersonWithGivenEmailFoundException;
import br.com.events.msauth.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements UserDetailsService {

    private final PersonRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return repository.findByEmailAndActiveTrue(username)
            .orElseThrow(
                NoPersonWithGivenEmailFoundException::new
            );
    }
}
