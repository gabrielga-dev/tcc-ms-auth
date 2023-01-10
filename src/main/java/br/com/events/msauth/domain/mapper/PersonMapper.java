package br.com.events.msauth.domain.mapper;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonMapper {

    public static Person convertToEntity(CreatePersonForm form){
        var toReturn = new Person();

        toReturn.setFirstName(form.getFirstName());
        toReturn.setLastName(form.getLastName());
        toReturn.setCpf(form.getCpf());
        toReturn.setEmail(form.getEmail());
        toReturn.setPassword(form.getPassword());

        return toReturn;
    }

    public static CreatePersonResult convertToResult(final Person saved) {
        return new CreatePersonResult(saved.getUuid());
    }
}
