package nvp_api.role.domain.repository;

import nvp_api.role.domain.aggregate.Role;

import java.util.Optional;

public interface RoleRepository {

    // 역할 이름으로 조회
    Optional<Role> findByName(String name);

    // 역할 고유 번호로 조회
    Optional<Role> findByRoleId(String roleId);

    // 역할 저장
    Optional<Role> save(Role role);

    // 역할 삭제
    void delete(Role role);
}
