package br.com.events.msauth.domain.form.person.changeEmail.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class ChangePersonEmailForm {


    @NotNull(message = "O campo do email não pode ser nulo.")
    @NotBlank(message = "O campo do email não pode estar vazio.")
    @Email(message = "O campo de email deve conter um email válido.")
    @Size(min = 5, max = 100, message = "O campo do email deve conter, pelo menos, 5 caracteres e no máximo 100.")
    private String newEmail;
}
