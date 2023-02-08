package br.com.events.msauth.domain.form.person.changeEmail.in;

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
public class ChangeEmailRequestUseCaseForm {

    private String newEmail;
    private String emailValidationUuid;
}
