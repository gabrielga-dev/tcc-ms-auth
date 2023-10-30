package br.com.events.msauth.business.use_case.email_validation;

import br.com.events.msauth.adapters.repository.EmailValidationRepository;
import br.com.events.msauth.domain.entity.EmailValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveEmailValidationUseCase {

    private final EmailValidationRepository emailValidationRepository;

    public EmailValidation execute(EmailValidation emailValidation){
        return emailValidationRepository.save(emailValidation);
    }
}
