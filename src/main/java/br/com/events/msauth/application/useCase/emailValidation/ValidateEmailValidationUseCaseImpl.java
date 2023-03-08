package br.com.events.msauth.application.useCase.emailValidation;

import br.com.events.msauth.application.useCase.exception.emailValidation.EmailValidationNotFoundException;
import br.com.events.msauth.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
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
