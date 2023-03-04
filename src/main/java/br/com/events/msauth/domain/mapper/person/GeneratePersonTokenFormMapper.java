package br.com.events.msauth.domain.mapper.person;

import br.com.events.msauth.domain.form.person.generateToken.in.GeneratePersonTokenForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * This class holds every needed method for mapping person related objects at {@link GeneratePersonTokenForm} class
 *
 * @author Gabriel Giumarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneratePersonTokenFormMapper {

    /**
     * This method parse a {@link GeneratePersonTokenForm} object into a {@link UsernamePasswordAuthenticationToken}
     *
     * @param form {@link GeneratePersonTokenForm} object with the data to be parsed
     * @return {@link UsernamePasswordAuthenticationToken} object with parsed data
     */
    public static UsernamePasswordAuthenticationToken convertToUsernamePasswordAuthenticationToken(
        GeneratePersonTokenForm form
    ) {
        return new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
    }
}
