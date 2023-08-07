package br.com.events.msauth.process.email_validation.create._use_case.interfaces;

/**
 * This interface dictates which methods are needed to create a new email validation to change a person's email
 *
 * @author Gabril Guimarães de Almeida
 */
public interface CreateEmailChangeEmailValidationUseCase {

    void execute(String personEmail);
}
