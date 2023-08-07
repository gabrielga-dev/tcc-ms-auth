package br.com.events.msauth.clean.process.email_validation.validate._use_case.interfaces;

/**
 * This interface dictates which methods are needed to validate a email validation
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface ValidatePersonCreationEmailValidationUseCase {

    void execute(String emailValidationUuid);
}
