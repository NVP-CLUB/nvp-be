package nvp_api.auth.infrastructure.repository;

import nvp_api.auth.domain.aggregate.MemberAuthentication;
import nvp_api.auth.domain.repository.MemberAuthenticationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberAuthenticationRepository extends MemberAuthenticationRepository, JpaRepository<MemberAuthentication, Long> {
}
