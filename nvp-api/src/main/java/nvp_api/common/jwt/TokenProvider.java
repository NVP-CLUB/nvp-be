package nvp_api.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import nvp_api.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final Key key;

    public TokenProvider(@Value("$(jwt.secret.key)") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDTO generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        long now = new Date().getTime();

        // 액세스 제한 시간 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        // AccessToken 생성
        String accessToken = Jwts.builder()                     // payload 예시
                .setSubject(authentication.getName())           //  "sub" : "userId"
                .claim("userNo", principal.getUserNo())      // "userNo" : "userNo"
                .claim(AUTHORITIES_KEY, authorities)            // "auth" : "ROLE_USER, ROLE_GUEST"
                .setExpiration(accessTokenExpiresIn)            // "exp" : 100000
                .signWith(key, SignatureAlgorithm.HS512)        // "alg" : "HS512"
                .compact();

        // 리프레쉬 제한 시간 생성
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        // AccessToken 생성
        String refreshToken = Jwts.builder()                     // payload 예시
                .setSubject(authentication.getName())           //  "sub" : "userId"
                .claim("userNo", principal.getUserNo())      // "userNo" : "userNo"
                .claim(AUTHORITIES_KEY, authorities)            // "auth" : "ROLE_USER, ROLE_GUEST"
                .setExpiration(refreshTokenExpiresIn)            // "exp" : 100000
                .signWith(key, SignatureAlgorithm.HS512)        // "alg" : "HS512"
                .compact();

        return TokenDTO.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .userNo(principal.getUserNo())
                .build();
    }
}
