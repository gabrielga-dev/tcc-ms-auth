package br.com.events.msauth.adapters.repository;

import br.com.events.msauth.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface makes the connection to the person database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<Person> findByEmailAndActiveTrue(String email);

    Optional<Person> findByCpfAndActiveTrue(String email);

    Optional<Person> findByUuidAndActiveTrue(String uuid);
}
