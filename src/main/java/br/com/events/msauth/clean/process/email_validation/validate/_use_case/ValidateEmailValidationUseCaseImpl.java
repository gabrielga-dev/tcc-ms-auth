package br.com.events.msauth.clean.process.email_validation.validate._use_case;

import br.com.events.msauth.clean.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.clean.domain.dto.email_validation.validate.in.ValidateEmailValidationDTO;
import br.com.events.msauth.clean.process.email_validation.validate._use_case.interfaces.ValidateEmailValidationUseCase;
import br.com.events.msauth.legacy.domain.repository.EmailValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidateEmailValidationUseCaseImpl implements ValidateEmailValidationUseCase {

    private final EmailValidationRepository repository;

    @Override
    public void execute(ValidateEmailValidationDTO form) {
        var validation = repository.findByUuidAndTypeAndValidatedIsFalse(
                form.getEmailValidationUuid(), form.getEmailValidationType()
        ).orElseThrow(
                EmailValidationNotFoundException::new
        );

        validation.setValidated(Boolean.TRUE);
        validation.setValidationDate(LocalDateTime.now());

        repository.save(validation);
    }
}
