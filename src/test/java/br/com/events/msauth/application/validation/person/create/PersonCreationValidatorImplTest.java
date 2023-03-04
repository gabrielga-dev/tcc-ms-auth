package br.com.events.msauth.application.validation.person.create;

import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * This class holds every needed test for {@link PersonCreationValidatorImpl}
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonCreationValidatorImplTest {

    private final PersonCreationValidatorImpl validator;

    public PersonCreationValidatorImplTest() {
        var mockedValidation = Mockito.mock(PersonCreationValidation.class);
        Mockito.doNothing().when(mockedValidation).validate(Mockito.any(CreatePersonUseCaseForm.class));

        var validations = List.of(mockedValidation);
        this.validator = new PersonCreationValidatorImpl(validations);
    }

    @Test
    @DisplayName("execute - when the incoming form is correct then no exception is thrown.")
    void execute_whenValidationGoesWell_thenNoExceptionIsThrow() {
        var form = CreatePersonUseCaseForm.builder().build();

        Assertions.assertDoesNotThrow(
            () -> validator.validate(form)
        );
    }
}
