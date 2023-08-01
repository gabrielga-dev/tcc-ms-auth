package br.com.events.msauth.clean.infrastructure.controller.entity.person.update.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class hold every needed information to update a person
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class UpdatePersonForm {

    @NotNull(message = "O campo do primeiro nome não pode ser nulo.")
    @NotBlank(message = "O campo do primeiro nome não pode estar vazio.")
    @Size(min = 3, message = "O campo do primeiro nome deve conter, pelo menos, 3 caracteres e no máximo 75.")
    @Size(max = 75, message = "O campo do primeiro nome deve conter, pelo menos, 3 caracteres e no máximo 75.")
    private String firstName;

    @NotNull(message = "O campo do último nome não pode ser nulo.")
    @NotBlank(message = "O campo do segundo nome não pode estar vazio.")
    @Size(min = 3, max = 75, message = "O campo do segundo nome deve conter, pelo menos, 3 caracteres e no máximo 150.")
    private String lastName;
}
