package br.com.events.msauth.domain.io.person.change_password.in;

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
public class ChangePasswordForm {

    private String password;
    private String passwordRepeated;
}
