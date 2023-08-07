package br.com.events.msauth.process.email_validation.email_validation_exists_and_not_validated._use_case;

import br.com.events.msauth.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.process.email_validation.email_validation_exists_and_not_validated._use_case.interfaces.CheckIfEmailValidationExistsAndIsNotValidatedUseCase;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
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
    public void execute(final String param) {
        repository.findByUuidAndValidatedIsFalse(param)
                .orElseThrow(
                        EmailValidationNotFoundException::new
                );
    }
}
