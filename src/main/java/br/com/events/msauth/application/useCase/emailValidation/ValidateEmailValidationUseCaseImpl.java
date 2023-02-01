package br.com.events.msauth.application.useCase.emailValidation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.useCase.exception.emailValidation.EmailValidationNotFoundException;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class implements {@link ValidateEmailValidationUseCase} interface to validate a person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ValidateEmailValidationUseCaseImpl implements ValidateEmailValidationUseCase {

    private final EmailValidationRepository repository;

    @Override
    public Void execute(final String param) {
        var validation = repository.findById(param)
            .orElseThrow(
                EmailValidationNotFoundException::new
            );

        validation.setValidated(Boolean.TRUE);
        validation.setValidationDate(LocalDateTime.now());

        repository.save(validation);
        return null;
    }
}
