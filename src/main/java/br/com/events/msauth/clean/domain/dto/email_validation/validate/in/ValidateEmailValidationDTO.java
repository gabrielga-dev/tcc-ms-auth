package br.com.events.msauth.clean.domain.dto.email_validation.validate.in;

import br.com.events.msauth.clean.domain.entity.EmailValidation;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
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
public class ValidateEmailValidationDTO {

    private String emailValidationUuid;
    private EmailValidationType emailValidationType;
}
