package nvp_api.member.infrastructure.repository;

import nvp_api.member.domain.aggregate.MemberProfile;
import nvp_api.member.domain.aggregate.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaMemberProfileRepository extends JpaRepository<MemberProfile, Long> {
    
    // profileId로 찾기
    Optional<MemberProfile> findByProfileId(long id);

    // 사용자 고유 번호로 찾기 (member_user_no)
    Optional<MemberProfile> findByMemberUser(MemberUser memberUser);

    // 등번호로 찾기
    List<MemberProfile> findAllByMemberNo(int memberNo);

    // 가입날짜로 찾기
    List<MemberProfile> findAllByCreatedAt(LocalDateTime registerDate);
}
