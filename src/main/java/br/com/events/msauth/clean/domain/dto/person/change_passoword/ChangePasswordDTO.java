package br.com.events.msauth.clean.domain.dto.person.change_passoword;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information for change a person's password
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class ChangePasswordDTO {

    private String emailValidationUuid;
    private String password;
    private String passwordRepeated;
}
