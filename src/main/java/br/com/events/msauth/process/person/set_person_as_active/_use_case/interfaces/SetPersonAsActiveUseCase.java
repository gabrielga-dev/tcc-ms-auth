package br.com.events.msauth.process.person.set_person_as_active._use_case.interfaces;

/**
 * This interface dictates that is needed only the person's uuid to see if the person account is valid
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface SetPersonAsActiveUseCase {

    void execute(String personUuid);
}
