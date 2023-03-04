package br.com.events.msauth.domain.form.person.changePassword.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class holds the needed information to change a person's password
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class ChangePasswordForm {

    @NotNull(message = "O campo da senha não pode ser nulo.")
    @NotBlank(message = "O campo da senha não pode estar vazio.")
    @Size(min = 5, max = 100, message = "O campo de senha deve conter, pelo menos, 8 caracteres e no máximo 15.")
    private String password;

    @NotNull(message = "O campo da repetição da senha não pode ser nulo.")
    @NotBlank(message = "O campo da repetição da senha não pode estar vazio.")
    @Size(min = 5, max = 100, message = "O campo de senha deve conter, pelo menos, 8 caracteres e no máximo 15.")
    private String passwordRepeated;
}
