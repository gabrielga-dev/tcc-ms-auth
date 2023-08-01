package br.com.events.msauth.legacy.application.useCase.emailValidation;

import br.com.events.msauth.clean.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.legacy.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.legacy.domain.repository.EmailValidationRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidateEmailValidationUseCaseImpl implements ValidateEmailValidationUseCase {

    private final EmailValidationRepository repository;

    @Override
    public Void execute(final ValidateEmailValidationUseCaseForm form) {

        var validation = repository.findByUuidAndTypeAndValidatedIsFalse(
            form.getEmailValidationUuid(), form.getEmailValidationType()
            ).orElseThrow(
                EmailValidationNotFoundException::new
            );

        validation.setValidated(Boolean.TRUE);
        validation.setValidationDate(LocalDateTime.now());

        repository.save(validation);

        return null;
    }
}
