package nvp_api.member.infrastructure.repository;

import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.member.domain.repository.MemberUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaMemberUserRepository extends JpaRepository<MemberUser, Long> {

    // userId로 찾기
    Optional<MemberUser> findByUserId(String userId);

    // 로그인 타입별 모두 찾기
    List<MemberUser> findByLoginType(String loginType);

    // username으로 찾기 (provider + "_" + providerId)
//    Optional<MemberUser> findByUsername(String username);

}
