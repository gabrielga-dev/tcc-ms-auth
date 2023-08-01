package br.com.events.msauth.legacy.application.useCase.emailValidation.personCreation;

import br.com.events.msauth.clean.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import br.com.events.msauth.legacy.domain.form.emailValidation.validateEmailValidation.in.ValidateEmailValidationUseCaseForm;
import br.com.events.msauth.legacy.domain.repository.EmailValidationRepository;
import br.com.events.msauth.legacy.infrastructure.useCase.emailConfirmation.ValidateEmailValidationUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.emailConfirmation.personCreation.ValidatePersonCreationEmailValidationUseCase;
import br.com.events.msauth.legacy.infrastructure.useCase.person.SetValidStatusOnPersonByEmailValidationsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
