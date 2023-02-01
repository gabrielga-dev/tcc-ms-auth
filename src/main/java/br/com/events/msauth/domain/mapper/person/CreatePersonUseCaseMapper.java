package br.com.events.msauth.domain.mapper.person;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.domain.form.person.create.out.CreatePersonUseCaseResult;
import br.com.events.msauth.infrastructure.useCase.person.CreatePersonUseCase;
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
     * This method maps a {@link CreatePersonUseCaseForm} object into a {@link Person} object
     *
     * @param form {@link CreatePersonUseCaseForm} object with the data to be mapped
     * @return {@link Person} object with the mapped data
     */
    public static Person convertToEntity(CreatePersonUseCaseForm form) {
        var toReturn = new Person();

        toReturn.setFirstName(form.getFirstName());
        toReturn.setLastName(form.getLastName());
        toReturn.setCpf(form.getCpf());
        toReturn.setEmail(form.getEmail());

        var encryptedPassword = new BCryptPasswordEncoder().encode(form.getPassword());
        toReturn.setPassword(encryptedPassword);

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
