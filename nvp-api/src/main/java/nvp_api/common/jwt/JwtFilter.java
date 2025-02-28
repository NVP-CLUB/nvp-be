package nvp_api.common.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.common.exception.CustomException;
import nvp_api.common.exception.ErrorCode;
import nvp_api.auth.infrastructure.repository.CrudBlackListRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_BEARER = "Bearer ";

    private final TokenProvider tokenProvider;
    private final CrudBlackListRepository blackListRepository;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 토큰 꺼내기
        String jwt = resolveToken(request);

        // 로그아웃 유무 확인
        blackListRepository.findByAccessToken(jwt).ifPresent(blackList -> {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        });

        // 토큰 유효성 검사 및 저장
        if (jwt != null && tokenProvider.validateToken(jwt)){
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWT_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
