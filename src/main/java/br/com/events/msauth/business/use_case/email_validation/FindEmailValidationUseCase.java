package br.com.events.msauth.business.use_case.email_validation;

import br.com.events.msauth.adapters.repository.EmailValidationRepository;
import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindEmailValidationUseCase {

    private final EmailValidationRepository emailValidationRepository;

    public Optional<EmailValidation> byUuid(String uuid){
        return emailValidationRepository.findById(uuid);
    }

    public Optional<EmailValidation> byUuidAndType(String uuid, EmailValidationType type){
        return emailValidationRepository.findByUuidAndTypeAndValidatedIsFalse(uuid, type);
    }
}
