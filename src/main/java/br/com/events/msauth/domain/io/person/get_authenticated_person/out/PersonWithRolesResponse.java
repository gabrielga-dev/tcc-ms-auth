package br.com.events.msauth.domain.io.person.get_authenticated_person.out;

import br.com.events.msauth.domain.entity.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class returns the result of the "get authenticated person information"
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
public class PersonWithRolesResponse extends PersonResponse{

    private List<RolesResponse> roles;

    public PersonWithRolesResponse(Person authenticatedPerson) {
        super(authenticatedPerson);
        this.roles = authenticatedPerson.getRoles()
                .stream()
                .map(RolesResponse::new)
                .collect(Collectors.toList());
    }
}
