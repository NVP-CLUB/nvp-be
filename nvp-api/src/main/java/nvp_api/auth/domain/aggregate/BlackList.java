package nvp_api.auth.domain.aggregate;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash(value = "black_list", timeToLive = 1000 * 60 * 30) // 유효 30분 설정
public class BlackList {
    @Id
    private String accessToken;

    @Indexed
    private String userId;
}
