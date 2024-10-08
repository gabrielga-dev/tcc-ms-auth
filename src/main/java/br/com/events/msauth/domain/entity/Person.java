package br.com.events.msauth.domain.entity;

import br.com.events.msauth.domain.entity.type.EmailValidationType;
import br.com.events.msauth.domain.io.person.create.in.CreatePersonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * This class represents the person's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "person")
public class Person implements UserDetails {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<EmailValidation> emailConfirmations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "person_role",
        joinColumns = @JoinColumn(name = "person_uuid"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Person(CreatePersonRequest request, String encryptedPassword, Role role) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.cpf = request.getCpf();
        this.email = request.getEmail();
        this.password = encryptedPassword;
        this.roles = Set.of(role);
    }
    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.emailConfirmations.stream()
            .filter(
                confirmation -> EmailValidationType.PERSON_CREATION.equals(confirmation.getType())
            ).allMatch(
                EmailValidation::isValidated
            );
    }
}
