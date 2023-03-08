package br.com.events.msauth.application.useCase.person;

import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.form.person.create.in.CreatePersonUseCaseForm;
import br.com.events.msauth.domain.repository.PersonRepository;
import br.com.events.msauth.infrastructure.validation.person.create.PersonCreationValidator;
import br.com.events.msauth.util.constants.TestConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * This class holds every needed test for {@link CreatePersonUseCaseImpl}
 *
 * @author Gabriel Guimarães de Almeida
 */
public class CreatePersonUseCaseImplTest {

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonCreationValidator validator;

    @InjectMocks
    private CreatePersonUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(validator).validate(any(CreatePersonUseCaseForm.class));
        when(repository.save(any(Person.class)))
            .thenAnswer(
                i -> {
                    var toReturn = (Person) i.getArguments()[0];
                    toReturn.setUuid(TestConstants.TEST_STR);
                    return toReturn;
                }
            );
    }

    @Test
    @DisplayName("execute - when the incoming form is correct then no exception is thrown.")
    void execute_whenValidationGoesWell_thenReturnCreatedUuid() {
        var form = CreatePersonUseCaseForm
            .builder()
            .cpf(TestConstants.TEST_STR)
            .build();

        var returned = useCase.execute(form);

        Assertions.assertEquals(TestConstants.TEST_STR, returned.getUuid());
    }
}
