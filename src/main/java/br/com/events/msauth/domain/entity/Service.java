package br.com.events.msauth.domain.entity;

import br.com.events.msauth.domain.entity.pk.ServicePk;
import br.com.events.msauth.domain.entity.type.ServiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * This class represents the service's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "service")
public class Service {

    @EmbeddedId
    private ServicePk pk;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ServiceType type;

    @MapsId("personUuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}
