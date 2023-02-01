package br.com.events.msauth.application.validation.person.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.events.msauth.application.validation.person.create.exception.PersonCreationPasswordEqualityException;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.util.constants.TestConstants;

/**
 * This class holds every needed test for {@link PersonCreationPasswordsEqualityValidation}
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonCreationPasswordsEqualityValidationTest {

    @Test
    @DisplayName("execute - when the incoming form has equals passwords then no exception is thrown.")
    void execute_whenPasswordsDoesMatchesMatches_thenNoExceptionIsThrown() {
        var validation = new PersonCreationPasswordsEqualityValidation();

        var form = CreatePersonUseCaseForm
            .builder()
            .password(TestConstants.TEST_STR)
            .passwordRepeated(TestConstants.TEST_STR)
            .build();

        Assertions.assertDoesNotThrow(
            () -> validation.validate(form)
        );
    }

    @Test
    @DisplayName("execute - when the incoming form doesnt have equals passwords then a exception is thrown.")
    void execute_whenPasswordsDoesNotMatchesMatches_thenPersonCreationPasswordEqualityExceptionIsThrown() {
        var validation = new PersonCreationPasswordsEqualityValidation();

        var form = CreatePersonUseCaseForm
            .builder()
            .password(TestConstants.TEST_STR)
            .passwordRepeated(TestConstants.TEST_STR + "1")
            .build();

        Assertions.assertThrows(
            PersonCreationPasswordEqualityException.class,
            () -> validation.validate(form)
        );
    }
}
