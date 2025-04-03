package nvp_api.auth.domain.repository;

import nvp_api.auth.domain.aggregate.MemberAuthentication;
import nvp_api.member.domain.aggregate.MemberUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberAuthenticationRepository {


    /**
     * ======================= 조회 ======================= //
     */
    // 전부 찾기
    List<MemberAuthentication> findAll();

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

    /**
     * ======================= 생성 ======================= //
     */
    // 저장
    MemberAuthentication save(MemberAuthentication memberAuthentication);

    // 전체 저장
    List<MemberAuthentication> saveAll(List<MemberAuthentication> memberAuthentications);

    /**
     * ======================= 삭제 ======================= //
     */
    // authenticationId 기반 삭제
    void deleteByAuthenticationId(long authenticationId);

    // 삭제
    void delete(MemberAuthentication memberAuthentication);

    // 전체 삭제
    void deleteAll(List<MemberAuthentication> memberAuthentications);

}
