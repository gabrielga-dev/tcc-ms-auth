package br.com.events.msauth.application.validation.person.changePassword;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.validation.person.create.exception.PersonCreationPasswordEqualityException;
import br.com.events.msauth.domain.form.person.changePassword.in.ChangePersonPasswordUseCaseForm;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.CheckIfEmailValidationExistsAndIsNotValidatedUseCase;
import br.com.events.msauth.infrastructure.validation.person.changePassword.ChangePersonPasswordValidation;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the {@link ChangePersonPasswordValidation} and validate if the incoming passwords
 * are identical If they aren't, then throws a {@link PersonCreationPasswordEqualityException}.
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ChangePasswordCheckIfEmailValidationExistsValidation implements ChangePersonPasswordValidation {

    private final CheckIfEmailValidationExistsAndIsNotValidatedUseCase checkIfEmailValidationExistsAndIsNotValidatedUseCase;

    @Override
    public void validate(final ChangePersonPasswordUseCaseForm toValidate) {
        checkIfEmailValidationExistsAndIsNotValidatedUseCase.execute(
            toValidate.getEmailValidationUuid()
        );
    }
}
