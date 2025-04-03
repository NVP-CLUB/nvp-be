package nvp_api.role.domain.repository;

import nvp_api.role.domain.aggregate.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    /**
     * ===조회===
     */
    // 역할 이름으로 조회
    Optional<Role> findByRoleName(String roleName);

    // 역할 고유 번호로 조회
    Optional<Role> findByRoleNo(long roleNo);

    /**
     * ===저장===
     */
    // 역할 저장
    Role save(Role role);

    // 역할 전체 저장
    List<Role> saveAll(List<Role> roles);

    /**
     * ===삭제===
     */
    // 역할 삭제
    void delete(Role role);

    // 역할 전체 삭제
    void deleteAll(List<Role> roles);
}
