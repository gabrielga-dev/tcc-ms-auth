package br.com.events.msauth.application.validation.person.create;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.events.msauth.application.validation.person.create.exception.PersonCreationEmailAvailableException;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonForm;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.util.constants.TestConstants;

/**
 * This class holds every needed test for {@link PersonCreationEmailAvailableValidation}
 *
 * @author Gabriel Guimarães de Almeida
 */
public class PersonCreationEmailAvailableValidationTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonCreationEmailAvailableValidation validation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("execute - when the incoming form has equals passwords then no exception is thrown.")
    void execute_whenEmailIsAvailable_thenNoExceptionIsThrown() {
        Mockito.when(repository.findByEmailAndActiveTrue(ArgumentMatchers.anyString()))
            .thenReturn(
                Optional.empty()
            );

        var form = CreatePersonForm
            .builder()
            .email(TestConstants.TEST_STR)
            .build();

        Assertions.assertDoesNotThrow(
            () -> validation.validate(form)
        );
    }

    @Test
    @DisplayName("execute - when the incoming form has non available email then a exception is thrown.")
    void execute_whenEmailIsNotAvailable_thenPersonCreationEmailAvailableExceptionExceptionIsThrown() {
        Mockito.when(repository.findByEmailAndActiveTrue(ArgumentMatchers.anyString()))
            .thenReturn(
                Optional.of(new Person())
            );

        var form = CreatePersonForm
            .builder()
            .email(TestConstants.TEST_STR)
            .build();

        Assertions.assertThrows(
            PersonCreationEmailAvailableException.class,
            () -> validation.validate(form)
        );
    }
}
