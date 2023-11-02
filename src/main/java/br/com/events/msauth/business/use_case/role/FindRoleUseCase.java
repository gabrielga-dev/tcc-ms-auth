package br.com.events.msauth.business.use_case.role;

import br.com.events.msauth.adapters.repository.RoleRepository;
import br.com.events.msauth.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindRoleUseCase {

    private final RoleRepository roleRepository;

    public Optional<Role> byId(Long id){
        return roleRepository.findById(id);
    }
}
