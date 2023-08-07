package br.com.events.msauth.infrastructure.controller.entity.person.generate_token.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class will be received to generate a person's JWT token
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class GeneratePersonTokenForm {

    @NotNull(message = "O campo do email não pode ser nulo.")
    @NotBlank(message = "O campo do email não pode estar vazio.")
    @Email(message = "O campo de email deve conter um email válido.")
    @Size(min = 5, max = 100, message = "O campo do email deve conter, pelo menos, 5 caracteres e no máximo 100.")
    private String username;

    @NotNull(message = "O campo da senha não pode ser nulo.")
    @NotBlank(message = "O campo da senha não pode estar vazio.")
    @Size(min = 5, max = 100, message = "O campo de senha deve conter, pelo menos, 8 caracteres e no máximo 15.")
    private String password;
}
