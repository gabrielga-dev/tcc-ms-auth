package br.com.events.msauth.domain.io.person.get_authenticated_person.out;

import br.com.events.msauth.domain.entity.Person;
import lombok.Getter;
import lombok.Setter;

/**
 * This class returns the result of the "get authenticated person information"
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
public class PersonResponse {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;

    public PersonResponse(Person authenticatedPerson) {
        this.uuid = authenticatedPerson.getUuid();
        this.firstName = authenticatedPerson.getFirstName();
        this.lastName = authenticatedPerson.getLastName();
        this.email = authenticatedPerson.getEmail();
        this.cpf = authenticatedPerson.getCpf();
    }
}
