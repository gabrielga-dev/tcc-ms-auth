package br.com.events.msauth.domain.mapper.person;

import br.com.events.msauth.domain.form.person.changePassword.in.ChangePasswordForm;
import br.com.events.msauth.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.infrastructure.useCase.person.ChangePersonPasswordUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class makes all mapping process that {@link ChangePersonPasswordUseCase} needs
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChangePersonPasswordUseCaseMapper {

    /**
     * This method parse a {@link ChangePasswordForm} object into a {@link ChangePersonPasswordUseCaseForm}
     *
     * @param form {@link ChangePasswordForm} object with the data to be parsed
     * @return {@link ChangePersonPasswordUseCaseForm} object with parsed data
     */
    public static ChangePersonPasswordUseCaseForm convertToUseCaseForm(ChangePasswordForm form){
        return ChangePersonPasswordUseCaseForm
            .builder()
            .password(form.getPassword())
            .passwordRepeated(form.getPasswordRepeated())
            .build();
    }
}
