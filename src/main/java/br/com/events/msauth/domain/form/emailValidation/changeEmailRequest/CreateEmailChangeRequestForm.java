package br.com.events.msauth.domain.form.emailValidation.changeEmailRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class hold the person's uuid that will be used to create a new email change request
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmailChangeRequestForm {

    @NotNull(message = "O campo do uuid não pode ser nulo.")
    @NotBlank(message = "O campo do uuid não pode estar vazio.")
    private String uuid;
}
