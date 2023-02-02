package br.com.events.msauth.application.useCase.emailValidation.personCreation;

import org.springframework.stereotype.Component;

import br.com.events.msauth.application.useCase.exception.emailValidation.EmailValidationNotFoundException;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.emailConfirmation.personCreation.ValidatePersonCreationEmailValidationUseCase;
import br.com.events.msauth.infrastructure.useCase.person.SetValidStatusOnPersonByEmailValidationsUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class implements {@link ValidatePersonCreationEmailValidationUseCase} interface to validate a person's email
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class ValidatePersonCreationEmailValidationUseCaseImpl implements ValidatePersonCreationEmailValidationUseCase {

    private final EmailValidationRepository repository;
    private final SetValidStatusOnPersonByEmailValidationsUseCase setValidStatusOnPersonByEmailValidationsUseCase;
    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;

    @Override
    public Void execute(final String emailValidationUuid) {

        var validationForm = ValidateEmailValidationUseCaseForm
            .builder()
            .emailValidationType(EmailValidationType.PERSON_CREATION)
            .emailValidationUuid(emailValidationUuid)
            .build();

        validateEmailValidationUseCase.execute(validationForm);

        var validation = repository.findById(emailValidationUuid)
            .orElseThrow(
                EmailValidationNotFoundException::new
            );

        setValidStatusOnPersonByEmailValidationsUseCase.execute(validation.getPerson().getUuid());

        return null;
    }
}
