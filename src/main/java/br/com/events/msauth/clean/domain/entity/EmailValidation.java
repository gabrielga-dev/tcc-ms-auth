package br.com.events.msauth.clean.domain.entity;

import br.com.events.msauth.clean.domain.entity.type.EmailValidationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class represents the email confirmation's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_validation")
public class EmailValidation {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_uuid", nullable = false)
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EmailValidationType type;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;

    @Column(name = "validated", nullable = false)
    private Boolean validated = Boolean.FALSE;
}
