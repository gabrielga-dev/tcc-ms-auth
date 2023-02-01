package br.com.events.msauth.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.events.msauth.domain.entity.EmailValidation;

/**
 * This interface makes the connection to the Email Validation database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface EmailValidationRepository extends JpaRepository<EmailValidation, String> {

    /**
     * This method find all {@link EmailValidation} that belongs to a person that has the given uuid
     *
     * @param personUuid {@link String} person uuid
     * @return {@link List}<{@link EmailValidation}> list of email validations
     */
    List<EmailValidation> findByPersonUuid(String personUuid);
}
