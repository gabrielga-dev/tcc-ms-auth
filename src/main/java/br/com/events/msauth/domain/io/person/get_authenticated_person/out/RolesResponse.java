package br.com.events.msauth.domain.io.person.get_authenticated_person.out;

import br.com.events.msauth.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesResponse {

    private String name;

    public RolesResponse(Role role) {
        this.name = role.getName();
    }
}
