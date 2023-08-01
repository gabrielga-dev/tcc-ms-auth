package br.com.events.msauth.legacy.domain.form.person.update.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class hold every needed information to update a person at the use case
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class UpdatePersonUseCaseForm {

    private String personUuid;
    private String firstName;
    private String lastName;
}
