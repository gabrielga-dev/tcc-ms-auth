package br.com.events.msauth.domain.mapper.person;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.findByUuid.out.FindPersonByUuidUseCaseResult;
import br.com.events.msauth.infrastructure.useCase.person.FindPersonByUuidUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at {@link FindPersonByUuidUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FindPersonByUuidUseCaseMapper {

    /**
     * This method maps a {@link Person} object into a {@link FindPersonByUuidUseCaseResult} object
     *
     * @param saved {@link Person} object with the data to be mapped
     * @return {@link FindPersonByUuidUseCaseResult} object with the mapped data
     */
    public static FindPersonByUuidUseCaseResult convertToResult(final Person saved) {
        return FindPersonByUuidUseCaseResult
            .builder()
            .uuid(saved.getUuid())
            .firstName(saved.getFirstName())
            .lastName(saved.getLastName())
            .cpf(saved.getCpf())
            .email(saved.getEmail())
            .build();
    }
}
