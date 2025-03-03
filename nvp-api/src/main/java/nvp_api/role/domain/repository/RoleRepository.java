package nvp_api.role.domain.repository;

import nvp_api.role.domain.aggregate.Role;

import java.util.Optional;

public interface RoleRepository {

    // 역할 이름으로 조회
    Optional<Role> findByRoleName(String roleName);

    // 역할 고유 번호로 조회
    Optional<Role> findByRoleNo(long roleNo);

    // 역할 삭제
    void delete(Role role);
}
