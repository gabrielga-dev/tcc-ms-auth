package br.com.events.msauth.clean.process.email_validation.create._use_case.interfaces;

/**
 * This interface dictates what is needed to create a new password change email validation
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreatePasswordChangeEmailValidationUseCase {

    void execute(String personEmail);
}
