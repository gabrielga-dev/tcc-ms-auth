package br.com.events.msauth.process.email_validation.validate._use_case;

import br.com.events.msauth.domain.exception._process.email_validation.find_by_uuid.EmailValidationNotFoundException;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.dto.email_validation.validate.in.ValidateEmailValidationDTO;
import br.com.events.msauth.process.email_validation.validate._use_case.interfaces.ValidateEmailValidationUseCase;
import br.com.events.msauth.domain.repository.EmailValidationRepository;
import br.com.events.msauth.process.email_validation.validate._use_case.interfaces.ValidatePersonCreationEmailValidationUseCase;
import br.com.events.msauth.process.person.set_person_as_active._use_case.interfaces.SetPersonAsActiveUseCase;
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

    private final ValidateEmailValidationUseCase validateEmailValidationUseCase;
    private final EmailValidationRepository repository;
    private final SetPersonAsActiveUseCase setPersonAsActiveUseCase;

    @Override
    public void execute(final String emailValidationUuid) {

        var validationForm = ValidateEmailValidationDTO
            .builder()
            .emailValidationType(EmailValidationType.PERSON_CREATION)
            .emailValidationUuid(emailValidationUuid)
            .build();

        validateEmailValidationUseCase.execute(validationForm);

        var validation = repository.findById(emailValidationUuid)
            .orElseThrow(
                EmailValidationNotFoundException::new
            );

        setPersonAsActiveUseCase.execute(validation.getPerson().getUuid());
    }
}
