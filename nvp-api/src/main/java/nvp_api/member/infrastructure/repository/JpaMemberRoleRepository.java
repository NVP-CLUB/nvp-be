package nvp_api.member.infrastructure.repository;

import nvp_api.member.domain.aggregate.MemberRole;
import nvp_api.member.domain.repository.MemberRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRoleRepository extends MemberRoleRepository, JpaRepository<MemberRole, Long> {
}
