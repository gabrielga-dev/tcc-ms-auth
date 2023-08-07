package br.com.events.msauth.infrastructure.controller.entity.person.generate_token.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class will hold every needed information about the generated JWT token
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class GeneratePersonTokenResult {

    private String token;

    private String type;
}
