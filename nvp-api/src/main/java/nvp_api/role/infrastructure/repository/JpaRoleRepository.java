package nvp_api.role.infrastructure.repository;

import nvp_api.role.domain.aggregate.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {

    // 역할 이름 기반 조회
    Optional<Role> findByRoleName(String roleName);
    
    // 역할 조회
    Optional<Role> findByRoleNo(long roleNo);
}
