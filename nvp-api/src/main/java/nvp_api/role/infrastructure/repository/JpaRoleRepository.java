package nvp_api.role.infrastructure.repository;

import nvp_api.role.domain.aggregate.Role;
import nvp_api.role.domain.repository.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends RoleRepository, JpaRepository<Role, Long> {


}
