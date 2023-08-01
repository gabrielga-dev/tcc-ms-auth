package br.com.events.msauth.legacy.domain.form.person.create.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every the new person's uuid
 *
 * @author Gabriel Giumarães de Almeida
 */
@Getter
@Setter
@AllArgsConstructor
public class CreatePersonUseCaseResult {

    private String uuid;
}
