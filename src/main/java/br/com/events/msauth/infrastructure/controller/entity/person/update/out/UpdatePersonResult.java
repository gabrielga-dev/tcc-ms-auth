package br.com.events.msauth.infrastructure.controller.entity.person.update.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class hold every updated information at person's data update process
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class UpdatePersonResult {

    private String firstName;
    private String lastName;
}
