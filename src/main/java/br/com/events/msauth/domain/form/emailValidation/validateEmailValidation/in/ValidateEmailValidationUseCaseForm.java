package br.com.events.msauth.domain.form.emailValidation.validateEmailValidation.in;

import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to validate a {@link EmailValidation}
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class ValidateEmailValidationUseCaseForm {

    private String emailValidationUuid;
    private EmailValidationType emailValidationType;
}
