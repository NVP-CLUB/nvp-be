package nvp_api.auth.domain.aggregate;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash(value = "black_list", timeToLive = 1000 * 60 * 60 * 24 * 7) // 유효 2주 설정
public class RefreshToken {

    @Id
    private String refreshToken;

    @Indexed
    private String userId;

}
