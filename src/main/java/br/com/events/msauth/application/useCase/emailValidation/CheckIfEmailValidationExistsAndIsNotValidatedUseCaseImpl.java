package br.com.events.msauth.application.useCase.emailValidation;

import br.com.events.msauth.application.useCase.exception.emailValidation.EmailValidationNotFoundException;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.CheckIfEmailValidationExistsAndIsNotValidatedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This implements {@link CheckIfEmailValidationExistsAndIsNotValidatedUseCase} interface to check if an email validation exists
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CheckIfEmailValidationExistsAndIsNotValidatedUseCaseImpl implements
    CheckIfEmailValidationExistsAndIsNotValidatedUseCase {

    private final EmailValidationRepository repository;

    @Override
    public Void execute(final String param) {
        repository.findByUuidAndValidatedIsFalse(param)
            .orElseThrow(
                EmailValidationNotFoundException::new
            );

        return null;
    }
}
