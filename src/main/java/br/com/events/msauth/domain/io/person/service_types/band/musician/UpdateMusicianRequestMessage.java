package br.com.events.msauth.domain.io.person.service_types.band.musician;

import br.com.events.msauth.domain.entity.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateMusicianRequestMessage {

    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String personUuid;

    public UpdateMusicianRequestMessage(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.cpf = person.getCpf();
        this.email = person.getEmail();
        this.personUuid = person.getUuid();
    }
}
