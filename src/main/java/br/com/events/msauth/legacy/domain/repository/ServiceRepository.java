package br.com.events.msauth.legacy.domain.repository;

import br.com.events.msauth.clean.domain.entity.Service;
import br.com.events.msauth.clean.domain.entity.pk.ServicePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface makes the connection to the service database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, ServicePk> {

}
