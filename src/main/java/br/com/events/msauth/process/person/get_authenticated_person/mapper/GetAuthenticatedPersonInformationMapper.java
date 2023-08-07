package br.com.events.msauth.process.person.get_authenticated_person.mapper;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.infrastructure.controller.entity.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;
import br.com.events.msauth.process.person.get_authenticated_person._use_case.interfaces.GetAuthenticatedPersonInformationUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at
 * {@link GetAuthenticatedPersonInformationUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetAuthenticatedPersonInformationMapper {

    /**
     * This method maps a {@link Person} object into a {@link GetAuthenticatedPersonInformationResult} object
     *
     * @param person {@link Person} object with the data to be mapped
     * @return {@link GetAuthenticatedPersonInformationResult} object with the mapped data
     */
    public static GetAuthenticatedPersonInformationResult convertToResult(Person person) {
        return GetAuthenticatedPersonInformationResult
            .builder()
            .uuid(person.getUuid())
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .email(person.getEmail())
            .build();
    }
}
