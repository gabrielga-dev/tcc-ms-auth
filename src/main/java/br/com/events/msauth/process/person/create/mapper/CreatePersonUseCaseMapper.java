package br.com.events.msauth.process.person.create.mapper;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.infrastructure.controller.entity.person.create.in.CreatePersonForm;
import br.com.events.msauth.process.person.create._use_case.interfaces.CreatePersonUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at {@link CreatePersonUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreatePersonUseCaseMapper {

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

        return toReturn;
    }
}
