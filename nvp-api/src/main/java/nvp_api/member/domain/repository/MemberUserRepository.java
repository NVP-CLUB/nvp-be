package nvp_api.member.domain.repository;

import nvp_api.member.domain.aggregate.MemberUser;

import java.util.List;
import java.util.Optional;

public interface MemberUserRepository {

    /**
     * ======================= 조회 ======================= //
      */
    // 모두 찾기
    List<MemberUser> findAll();

    // userId로 찾기
    Optional<MemberUser> findByUserId(String userId);

    // 로그인 타입별 모두 찾기
    List<MemberUser> findByLoginType(String loginType);

    // username으로 찾기 (provider + "_" + providerId)
//    Optional<MemberUser> findByUsername(String username);

    /**
     * ======================= 생성 ======================= //
     */
    // 저장
    MemberUser save(MemberUser memberUser);

    // 전체 저장
    List<MemberUser> saveAll(List<MemberUser> memberUsers);

    /**
     * ======================= 삭제 ======================= //
     */
    // 삭제
    void delete(MemberUser memberUser);

    // 전체 삭제
    void deleteAll(List<MemberUser> memberUsers);
}
