package nvp_api.auth.infrastructure.repository;

import nvp_api.auth.domain.aggregate.AuthPassword;
import nvp_api.auth.domain.repository.AuthPasswordRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAuthPasswordRepository extends AuthPasswordRepository, JpaRepository<AuthPassword, Long> {
}
