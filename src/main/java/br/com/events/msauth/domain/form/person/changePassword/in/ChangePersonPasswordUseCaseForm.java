package br.com.events.msauth.domain.form.person.changePassword.in;

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
public class ChangePersonPasswordUseCaseForm {

    private String emailValidationUuid;
    private String password;
    private String passwordRepeated;
}
