package br.com.events.msauth.business.use_case.email_validation;

import br.com.events.msauth.business.exception.email_validation.EmailValidationNotFoundException;
import br.com.events.msauth.domain.entity.EmailValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateEmailValidationUseCase {

    private final FindEmailValidationUseCase findEmailValidationUseCase;
    private final SaveEmailValidationUseCase saveEmailValidationUseCase;

    public void byUuid(String emailValidationUuid){
        var emailValidation = findEmailValidationUseCase.byUuid(emailValidationUuid)
                .orElseThrow(EmailValidationNotFoundException::new);
        this.execute(emailValidation);
    }

     public void execute(EmailValidation emailValidation){
         if (emailValidation.isValidated()){
             throw new EmailValidationNotFoundException();
         }

         emailValidation.setValidated(true);
        saveEmailValidationUseCase.execute(emailValidation);
     }
}
