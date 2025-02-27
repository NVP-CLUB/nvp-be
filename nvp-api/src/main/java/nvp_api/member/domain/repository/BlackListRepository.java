package nvp_api.member.domain.repository;

import nvp_api.member.domain.aggregate.BlackList;

import java.util.Optional;

public interface BlackListRepository {

    /**
     * ======================= 저장 ======================= //
     */
    
    // 블랙리스트 등록
    Optional<BlackList> save(BlackList blackList);

    /**
     * ======================= 조회 ======================= //
     */
    
    // AccessToken으로 찾기
    Optional<BlackList> findByAccessToken(String accessToken);
    
    // userId로 찾기
    Optional<BlackList> findByUserId(String userId);
}
