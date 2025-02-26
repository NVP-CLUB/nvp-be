package nvp_api.member.infrastructure.repository;

import nvp_api.member.domain.aggregate.MemberProfile;
import nvp_api.member.domain.repository.MemberProfileRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberProfileRepository extends MemberProfileRepository, JpaRepository<MemberProfile, Long> {
}
