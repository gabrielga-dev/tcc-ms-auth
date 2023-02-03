package br.com.events.msauth.domain.mapper.person;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.update.in.UpdatePersonForm;
import br.com.events.msauth.domain.form.person.update.in.UpdatePersonUseCaseForm;
import br.com.events.msauth.domain.form.person.update.out.UpdatePersonResult;
import br.com.events.msauth.infrastructure.useCase.person.UpdatePersonUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class makes all mapping process that {@link UpdatePersonUseCase} needs
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdatePersonUseCaseMapper {

    /**
     * This method parse a {@link UpdatePersonForm} object into a {@link UpdatePersonUseCaseForm}
     *
     * @param form {@link UpdatePersonForm} object with the data to be parsed
     * @return {@link UpdatePersonUseCaseForm} object with parsed data
     */
    public static UpdatePersonUseCaseForm convertToUseCaseForm(UpdatePersonForm form){
        return UpdatePersonUseCaseForm
            .builder()
            .firstName(form.getFirstName())
            .lastName(form.getLastName())
            .build();
    }

    /**
     * This method parse a {@link UpdatePersonForm} object into a {@link UpdatePersonUseCaseForm}
     *
     * @param person {@link UpdatePersonForm} object with the data to be parsed
     * @return {@link UpdatePersonUseCaseForm} object with parsed data
     */
    public static UpdatePersonResult convertToResult(Person person){
        return UpdatePersonResult
            .builder()
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .build();
    }
}
