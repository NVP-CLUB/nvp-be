package nvp_api.auth.domain.repository;

import nvp_api.auth.domain.aggregate.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {

    /**
     * ======================= 조회 ======================= //
     */

    // AccessToken으로 찾기
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    // userId로 찾기
    Optional<RefreshToken> findByUserId(String userId);
}
