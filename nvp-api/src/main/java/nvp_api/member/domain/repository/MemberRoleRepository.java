package nvp_api.member.domain.repository;

import nvp_api.member.domain.aggregate.MemberRole;

import java.util.List;
import java.util.Optional;

public interface MemberRoleRepository {

    /**
     * ======================= 저장 ======================= //
     */

    // 저장
    Optional<MemberRole> save(MemberRole memberRole);

    // 모두 저장
    List<MemberRole> saveAll(List<MemberRole> memberRoles);

    /**
     * ======================= 조회 ======================= //
     */

    // 고유 번호로 찾기
    Optional<MemberRole> findByMemberRoleNo(Long memberRoleNo);

    // 전체 조회
    List<MemberRole> findAll();

    // 유저 고유 번호로 권한 모두 찾기
    List<MemberRole> findAllByUserNo(Long userNo);

    // 권한별 찾기
    List<MemberRole> findAllByRoleNo(Long roleNo);

    /**
     * ======================= 삭제 ======================= //
     */

    // 단일 삭제
    void delete(MemberRole memberRole);

    // 전체 삭제
    void deleteAll(List<MemberRole> memberRoles);

}
