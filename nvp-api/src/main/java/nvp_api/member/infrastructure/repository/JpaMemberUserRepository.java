package nvp_api.member.infrastructure.repository;

import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.member.domain.repository.MemberUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberUserRepository extends MemberUserRepository, JpaRepository<MemberUser, Long> {

}
