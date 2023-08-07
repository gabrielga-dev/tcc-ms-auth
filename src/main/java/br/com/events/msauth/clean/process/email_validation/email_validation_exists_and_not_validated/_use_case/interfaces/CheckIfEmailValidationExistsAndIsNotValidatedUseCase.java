package br.com.events.msauth.clean.process.email_validation.email_validation_exists_and_not_validated._use_case.interfaces;

/**
 * This interface dictates which methods are needed to check if an email validation exists
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CheckIfEmailValidationExistsAndIsNotValidatedUseCase {

    void execute(String emailValidationUuid);
}
