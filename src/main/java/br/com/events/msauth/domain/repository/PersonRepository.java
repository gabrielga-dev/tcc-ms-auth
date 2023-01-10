package br.com.events.msauth.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.events.msauth.domain.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<Person> findByEmailAndActiveTrue(String email);

    Optional<Person> findByCpfAndActiveTrue(String email);
}
