package br.com.events.msauth.domain.mapper;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonMapper {

    /**
     * This method maps a {@link CreatePersonForm} object into a {@link Person} object
     *
     * @param form {@link CreatePersonForm} object with the data to be mapped
     * @return {@link Person} object with the mapped data
     */
    public static Person convertToEntity(CreatePersonForm form) {
        var toReturn = new Person();

        toReturn.setFirstName(form.getFirstName());
        toReturn.setLastName(form.getLastName());
        toReturn.setCpf(form.getCpf());
        toReturn.setEmail(form.getEmail());
        toReturn.setPassword(form.getPassword());

        return toReturn;
    }

    /**
     * This method maps a {@link Person} object into a {@link CreatePersonResult} object
     *
     * @param saved {@link Person} object with the data to be mapped
     * @return {@link CreatePersonResult} object with the mapped data
     */
    public static CreatePersonResult convertToResult(final Person saved) {
        return new CreatePersonResult(saved.getUuid());
    }
}
