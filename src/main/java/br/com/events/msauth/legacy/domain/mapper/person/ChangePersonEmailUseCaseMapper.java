package br.com.events.msauth.legacy.domain.mapper.person;

import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangeEmailRequestUseCaseForm;
import br.com.events.msauth.legacy.domain.form.person.changeEmail.in.ChangePersonEmailForm;
import br.com.events.msauth.legacy.infrastructure.useCase.person.ChangePersonEmailUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed method for mapping person related objects at {@link ChangePersonEmailUseCase} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChangePersonEmailUseCaseMapper {


    public static ChangeEmailRequestUseCaseForm convertToUseCaseForm(ChangePersonEmailForm origin){
        return ChangeEmailRequestUseCaseForm
            .builder()
            .newEmail(origin.getNewEmail())
            .build();
    }
}
