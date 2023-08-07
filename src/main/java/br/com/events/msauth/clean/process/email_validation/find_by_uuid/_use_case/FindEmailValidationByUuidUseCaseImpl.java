package br.com.events.msauth.clean.process.email_validation.find_by_uuid._use_case;

import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.process.email_validation.find_by_uuid._use_case.interfaces.FindEmailValidationByUuidUseCase;
import br.com.events.msauth.clean.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.clean.domain.repository.EmailValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindEmailValidationByUuidUseCaseImpl implements FindEmailValidationByUuidUseCase {

    private final EmailValidationRepository repository;

    @Override
    public EmailValidation execute(String emailValidationUuid) {
        return repository.findById(emailValidationUuid)
                .orElseThrow(
                        EmailValidationNotFoundException::new
                );
    }
}
