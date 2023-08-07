package br.com.events.msauth.domain.dto.person.change_email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to change a person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class ChangeEmailDTO {

    private String emailValidationUuid;
    private String newEmail;
}
