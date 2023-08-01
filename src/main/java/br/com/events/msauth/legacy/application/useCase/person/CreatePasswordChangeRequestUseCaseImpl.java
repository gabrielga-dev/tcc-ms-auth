package br.com.events.msauth.legacy.application.useCase.person;

import br.com.events.msauth.legacy.domain.form.emailValidation.passwordChangeRequest.in.CreatePasswordChangeRequestForm;
import br.com.events.msauth.legacy.infrastructure.useCase.person.CreatePasswordChangeRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements {@link CreatePasswordChangeRequestUseCase} and creates a new password change request
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreatePasswordChangeRequestUseCaseImpl implements CreatePasswordChangeRequestUseCase {

    @Override
    public Void execute(final CreatePasswordChangeRequestForm param) {
        return null;
    }
}
