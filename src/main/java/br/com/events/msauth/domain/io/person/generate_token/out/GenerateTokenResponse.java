package br.com.events.msauth.domain.io.person.generate_token.out;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class will hold every needed information about the generated JWT token
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@RequiredArgsConstructor
public class GenerateTokenResponse {

    private final String token;
    private final String type = "Bearer";
}
