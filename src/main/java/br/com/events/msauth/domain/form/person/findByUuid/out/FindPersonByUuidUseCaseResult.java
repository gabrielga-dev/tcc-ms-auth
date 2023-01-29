package br.com.events.msauth.domain.form.person.findByUuid.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindPersonByUuidUseCaseResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
}
