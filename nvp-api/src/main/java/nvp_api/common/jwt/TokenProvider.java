package nvp_api.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import nvp_api.common.exception.CustomException;
import nvp_api.common.exception.ErrorCode;
import nvp_api.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
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

    // 권한
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
        log.info("claims 확인 {}", claims);
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        log.info("claims sub {}", claims.getSubject());
        log.info("claims second {}", claims.get("userNo", Long.class));
        log.info("accessToken 값 확인 {}", accessToken);

        // UsernamePasswordAuthenticationToken에 커스텀 객체 넣기
        TokenSaveDTO principal =
                new TokenSaveDTO(
                        Long.valueOf(claims.get("userNo").toString())
                        ,claims.getSubject(),
                        authorities,
                        accessToken
                );

        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new CustomException(ErrorCode.INVALID_JWT_SIGNATURE);

        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);

        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new CustomException(ErrorCode.UNSUPPORTED_JWT_TOKEN);

        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new CustomException(ErrorCode.MALFORMED_JWT_TOKEN);
        }

    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }
    }

}
