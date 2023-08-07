package br.com.events.msauth.domain.repository;

import br.com.events.msauth.domain.entity.EmailValidation;
import br.com.events.msauth.domain.entity.type.EmailValidationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * This method searches for a {@link EmailValidation} that has the same uuid, and it's not validated
     *
     * @param uuid {@link String} {@link EmailValidation}'s uuid
     * @return {@link Optional}<{@link EmailValidation}> optional with the search result
     */
    Optional<EmailValidation> findByUuidAndValidatedIsFalse(String uuid);

    /**
     * This method searches for a {@link EmailValidation} that has the same uuid and type, and it's not validated
     *
     * @param uuid {@link String} {@link EmailValidation}'s uuid
     * @param type  {@link EmailValidationType} email validation type
     * @return {@link Optional}<{@link EmailValidation}> optional with the search result
     */
    Optional<EmailValidation> findByUuidAndTypeAndValidatedIsFalse(String uuid, EmailValidationType type);


}
