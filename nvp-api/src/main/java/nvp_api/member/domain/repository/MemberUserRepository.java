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
    List<MemberUser> findByLoginType(MemberUser.LoginType loginType);

    // username으로 찾기 (provider + "_" + providerId)
    Optional<MemberUser> findByUsername(String username);

    /**
     * ======================= 삭제 ======================= //
     */

    // 삭제
    void delete(MemberUser memberUser);

}
