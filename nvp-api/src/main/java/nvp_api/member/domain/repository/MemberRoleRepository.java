package nvp_api.member.domain.repository;

import nvp_api.member.domain.aggregate.MemberRole;
import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.role.domain.aggregate.Role;

import java.util.List;
import java.util.Optional;

public interface MemberRoleRepository {

    /**
     * ======================= 조회 ======================= //
     */

    // 고유 번호로 찾기
    Optional<MemberRole> findByMemberRoleNo(Long memberRoleNo);

    // 전체 조회
    List<MemberRole> findAll();

    // 유저 고유 번호로 권한 모두 찾기
    List<MemberRole> findAllByMemberUser(MemberUser memberUser);

    // 권한별 찾기
    List<MemberRole> findAllByRole(Role role);

    /**
     * ======================= 삭제 ======================= //
     */

    // 단일 삭제
    void delete(MemberRole memberRole);

}
