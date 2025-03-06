package nvp_api.auth.infrastructure.repository;

import nvp_api.auth.domain.aggregate.RefreshToken;
import nvp_api.auth.domain.repository.RefreshTokenRepository;
import org.springframework.data.repository.CrudRepository;

public interface CrudRefreshTokenRepository extends RefreshTokenRepository, CrudRepository<RefreshToken, String> {
}
