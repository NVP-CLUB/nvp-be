package nvp_api.member.infrastructure.repository;

import nvp_api.member.domain.aggregate.MemberRole;
import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.role.domain.aggregate.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaMemberRoleRepository extends JpaRepository<MemberRole, Long> {

    // 고유 번호로 찾기
    Optional<MemberRole> findByMemberRoleNo(Long memberRoleNo);

    // 유저 고유 번호로 권한 모두 찾기
    List<MemberRole> findAllByMemberUser(MemberUser memberUser);

    // 권한별 찾기
    List<MemberRole> findAllByRole(Role role);
}
