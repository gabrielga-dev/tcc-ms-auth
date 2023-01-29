package br.com.events.msauth.application.useCase.emailValidation;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.useCase.exception.emailValidation.EmailValidationNotFoundException;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.CheckIfEmailValidationExistsUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This implements {@link CheckIfEmailValidationExistsUseCase} interface to check if an email validation exists
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CheckIfEmailValidationExistsUseCaseImpl implements CheckIfEmailValidationExistsUseCase {

    private final EmailValidationRepository repository;

    @Override
    public Void execute(final String param) {
        repository.findById(param)
            .orElseThrow(
                EmailValidationNotFoundException::new
            );

        return null;
    }
}
