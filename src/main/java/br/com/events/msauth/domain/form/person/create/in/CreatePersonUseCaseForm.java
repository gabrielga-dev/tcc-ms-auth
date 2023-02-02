package br.com.events.msauth.domain.form.person.create.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed data for creating a new person and it's validations
 *
 * @author Gabriel Giumarães de Almeida
 */
@Getter
@Setter
@Builder
public class CreatePersonUseCaseForm {

    @NotNull(message = "O campo do primeiro nome não pode ser nulo.")
    @NotBlank(message = "O campo do primeiro nome não pode estar vazio.")
    @Size(min = 3, message = "O campo do primeiro nome deve conter, pelo menos, 3 caracteres e no máximo 75.")
    @Size(max = 75, message = "O campo do primeiro nome deve conter, pelo menos, 3 caracteres e no máximo 75.")
    private String firstName;

    @NotNull(message = "O campo do último nome não pode ser nulo.")
    @NotBlank(message = "O campo do segundo nome não pode estar vazio.")
    @Size(min = 3, max = 75, message = "O campo do segundo nome deve conter, pelo menos, 3 caracteres e no máximo 150.")
    private String lastName;


    @NotNull(message = "O campo de CPF não pode ser nulo.")
    @NotBlank(message = "O campo do CPF não pode estar vazio.")
    @CPF(message = "O campo do CPF deve conter um CPF válido.")
    @Size(min = 11, max = 14, message = "O campo do CPF deve conter, pelo menos, 11 caracteres e no máximo 14.")
    private String cpf;

    @NotNull(message = "O campo do email não pode ser nulo.")
    @NotBlank(message = "O campo do email não pode estar vazio.")
    @Email(message = "O campo de email deve conter um email válido.")
    @Size(min = 5, max = 100, message = "O campo do email deve conter, pelo menos, 5 caracteres e no máximo 100.")
    private String email;

    @NotNull(message = "O campo da senha não pode ser nulo.")
    @NotBlank(message = "O campo da senha não pode estar vazio.")
    @Size(min = 5, max = 100, message = "O campo de senha deve conter, pelo menos, 8 caracteres e no máximo 15.")
    private String password;

    @NotNull(message = "O campo da repetição da senha não pode ser nulo.")
    @NotBlank(message = "O campo da repetição da senha não pode estar vazio.")
    @Size(min = 5, max = 100, message = "O campo de senha deve conter, pelo menos, 8 caracteres e no máximo 15.")
    private String passwordRepeated;
}
