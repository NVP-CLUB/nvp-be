package nvp_api.member.domain.repository;

import nvp_api.member.domain.aggregate.MemberProfile;
import nvp_api.member.domain.aggregate.MemberUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberProfileRepository {

    /**
     * ======================= 조회 ======================= //
     */
    // 모두 찾기
    List<MemberProfile> findAll();

    // profileId로 찾기
    Optional<MemberProfile> findByProfileId(long id);

    // 사용자 고유 번호로 찾기 (member_user_no)
    Optional<MemberProfile> findByMemberUser(MemberUser memberUser);

    // 등번호로 찾기
    List<MemberProfile> findAllByMemberNo(int memberNo);

    // 가입날짜로 찾기
    List<MemberProfile> findAllByCreatedAt(LocalDateTime registerDate);

    /**
     * ======================= 생성 ======================= //
     */
    // 저장
    MemberProfile save(MemberProfile memberProfile);

    // 전체 저장
    List<MemberProfile> saveAll(List<MemberProfile> memberProfiles);

    /**
     * ======================= 삭제 ======================= //
     */
    // 삭제
    void delete(MemberProfile memberProfile);

    // 전체 삭제
    void deleteAll(List<MemberProfile> memberProfiles);
}
