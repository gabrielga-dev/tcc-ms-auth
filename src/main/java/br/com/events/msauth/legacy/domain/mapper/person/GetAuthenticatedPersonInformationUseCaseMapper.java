package br.com.events.msauth.legacy.domain.mapper.person;

import br.com.events.msauth.clean.domain.entity.Person;
import br.com.events.msauth.legacy.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationResult;
import br.com.events.msauth.legacy.domain.form.person.getAuthenticatedPersonInformation.out.GetAuthenticatedPersonInformationUseCaseResult;
import br.com.events.msauth.legacy.infrastructure.useCase.person.GetAuthenticatedPersonInformationUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at
 * {@link GetAuthenticatedPersonInformationUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetAuthenticatedPersonInformationUseCaseMapper {

    /**
     * This method maps a {@link Person} object into a {@link GetAuthenticatedPersonInformationUseCaseResult} object
     *
     * @param person {@link Person} object with the data to be mapped
     * @return {@link GetAuthenticatedPersonInformationUseCaseResult} object with the mapped data
     */
    public static GetAuthenticatedPersonInformationUseCaseResult convertToResult(Person person) {
        return GetAuthenticatedPersonInformationUseCaseResult
            .builder()
            .uuid(person.getUuid())
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .email(person.getEmail())
            .build();
    }

    /**
     * This method maps a {@link GetAuthenticatedPersonInformationUseCaseResult} object into a
     * {@link GetAuthenticatedPersonInformationResult} object
     *
     * @param person {@link GetAuthenticatedPersonInformationUseCaseResult} object with the data to be mapped
     * @return {@link GetAuthenticatedPersonInformationResult} object with the mapped data
     */
    public static GetAuthenticatedPersonInformationResult convertToResult(
        GetAuthenticatedPersonInformationUseCaseResult person) {
        return GetAuthenticatedPersonInformationResult
            .builder()
            .uuid(person.getUuid())
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .email(person.getEmail())
            .build();
    }
}
