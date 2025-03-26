package nvp_api.security.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import nvp_api.auth.application.dto.CustomOAuth2User;
import nvp_api.common.jwt.TokenDTO;
import nvp_api.common.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        TokenDTO tokenDTO = tokenProvider.generateSocialToken(authentication);

        // AccessToken을 응답 헤더에 추가
        response.setHeader("Authorization", "Bearer " + tokenDTO.getAccessToken());

        // RefreshToken을 HttpOnly 쿠키에 저장
        response.addCookie(createCookie("refreshToken", tokenDTO.getRefreshToken()));

        // Redirect
        response.sendRedirect("http://localhost:5173/");
    }

    private Cookie createCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*24*7);       // 7일 유지
        //cookie.setSecure(true);           // HTTPS 환경에서만 전송
        cookie.setPath("/");                // 모든 경로에서 사용 가능
        cookie.setHttpOnly(true);           // JavaScript에서 접근 불가

        return cookie;
    }
}
