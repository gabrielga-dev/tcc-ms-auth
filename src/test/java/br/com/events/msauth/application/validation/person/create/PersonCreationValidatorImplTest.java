package br.com.events.msauth.application.validation.person.create;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidation;

/**
 * This class holds every needed test for {@link PersonCreationValidatorImpl}
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonCreationValidatorImplTest {

    private final PersonCreationValidatorImpl validator;

    public PersonCreationValidatorImplTest() {
        var mockedValidation = Mockito.mock(PersonCreationValidation.class);
        Mockito.doNothing().when(mockedValidation).validate(Mockito.any(CreatePersonForm.class));

        var validations = List.of(mockedValidation);
        this.validator = new PersonCreationValidatorImpl(validations);
    }

    @Test
    @DisplayName("execute - when the incoming form is correct then no exception is thrown.")
    void execute_whenValidationGoesWell_thenNoExceptionIsThrow() {
        var form = CreatePersonForm.builder().build();

        Assertions.assertDoesNotThrow(
            () -> validator.validate(form)
        );
    }
}
