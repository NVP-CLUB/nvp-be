package nvp_api.role.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import nvp_api.role.domain.aggregate.Role;
import nvp_api.role.domain.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaRoleRepositoryAdapter implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return jpaRoleRepository.findByRoleName(roleName);
    }

    @Override
    public Optional<Role> findByRoleNo(long roleNo) {
        return jpaRoleRepository.findByRoleNo(roleNo);
    }

    @Override
    public Role save(Role role) {
        return jpaRoleRepository.save(role);
    }

    @Override
    public List<Role> saveAll(List<Role> roles) {
        return jpaRoleRepository.saveAll(roles);
    }

    @Override
    public void delete(Role role) {
        jpaRoleRepository.delete(role);
    }

    @Override
    public void deleteAll(List<Role> roles) {
        jpaRoleRepository.deleteAll(roles);
    }
}
