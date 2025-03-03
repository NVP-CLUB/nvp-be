package nvp_api.auth.domain.repository;

import nvp_api.auth.domain.aggregate.BlackList;

import java.util.Optional;

public interface BlackListRepository {

    /**
     * ======================= 조회 ======================= //
     */
    
    // AccessToken으로 찾기
    Optional<BlackList> findByAccessToken(String accessToken);
    
    // userId로 찾기
    Optional<BlackList> findByUserId(String userId);
}
