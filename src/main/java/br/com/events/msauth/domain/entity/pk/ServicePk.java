package br.com.events.msauth.domain.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * This class represents the service's primary key
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ServicePk implements Serializable {

    @Column(name = "person_uuid", nullable = false)
    private String personUuid;

    @Column(name = "service_uuid", nullable = false)
    private String serviceUuid;
}
