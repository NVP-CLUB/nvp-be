package nvp_api.auth.infrastructure.repository;

import nvp_api.auth.domain.aggregate.MemberAuthentication;
import nvp_api.member.domain.aggregate.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JpaMemberAuthenticationRepository extends JpaRepository<MemberAuthentication, Long> {

    // 사용자 정보 고유 번호로 찾기
    Optional<MemberAuthentication> findByAuthenticationId(long authenticationId);

    // 활동 여부별로 찾기
    List<MemberAuthentication> findAllByIsPublic(boolean istPublic);

    // 생일로 찾기
    List<MemberAuthentication> findAllByBirthday(LocalDate birthday);

    // 이름으로 찾기
    List<MemberAuthentication> findAllByName(String name);

    // memberUser로 찾기
    Optional<MemberAuthentication> findByMemberUser(MemberUser memberUser);

    // authenticationId 기반 삭제
    void deleteByAuthenticationId(long authenticationId);
}
