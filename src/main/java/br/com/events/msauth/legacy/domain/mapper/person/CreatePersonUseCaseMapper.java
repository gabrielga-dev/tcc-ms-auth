package br.com.events.msauth.legacy.domain.mapper.person;

import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.clean.infrastructure.controller.entity.person.create.CreatePersonForm;
import br.com.events.msauth.legacy.domain.form.person.create.out.CreatePersonUseCaseResult;
import br.com.events.msauth.clean.process.use_case.person.interfaces.CreatePersonUseCase;
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

    /**
     * This method maps a {@link Person} object into a {@link CreatePersonUseCaseResult} object
     *
     * @param saved {@link Person} object with the data to be mapped
     * @return {@link CreatePersonUseCaseResult} object with the mapped data
     */
    public static CreatePersonUseCaseResult convertToResult(final Person saved) {
        return new CreatePersonUseCaseResult(saved.getUuid());
    }
}
