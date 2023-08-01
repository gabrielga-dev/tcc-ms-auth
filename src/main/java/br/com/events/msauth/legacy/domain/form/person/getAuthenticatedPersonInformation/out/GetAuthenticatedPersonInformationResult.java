package br.com.events.msauth.legacy.domain.form.person.getAuthenticatedPersonInformation.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class returns the result of the "get authenticated person information"
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class GetAuthenticatedPersonInformationResult {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
}
