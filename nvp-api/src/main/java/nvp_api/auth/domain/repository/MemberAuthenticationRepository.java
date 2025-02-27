package nvp_api.auth.domain.repository;

import nvp_api.auth.domain.aggregate.MemberAuthentication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberAuthenticationRepository {

    /**
     * ======================= 저장 ======================= //
     */

    // 저장
    Optional<MemberAuthentication> save(MemberAuthentication memberAuthentication);

    // 전부 저장
    List<MemberAuthentication> saveAll(List<MemberAuthentication> memberAuthentications);

    /**
     * ======================= 조회 ======================= //
     */

    // 전부 찾기
    List<MemberAuthentication> findAll();

    // 사용자 정보 고유 번호로 찾기
    Optional<MemberAuthentication> findByAuthenticationId(long authenticationId);

    // 활동 여부별로 찾기
    List<MemberAuthentication> findAllByIstPublic(boolean istPublic);

    // 생일로 찾기
    List<MemberAuthentication> findAllByBirthday(LocalDate birthday);

    // 이름으로 찾기
    List<MemberAuthentication> findAllByMemberName(String memberName);

    /**
     * ======================= 삭제 ======================= //
     */

    // 삭제
    void deleteByAuthenticationId(long authenticationId);

    // 전부 삭제
    void deleteAll(List<MemberAuthentication> authentications);

}
