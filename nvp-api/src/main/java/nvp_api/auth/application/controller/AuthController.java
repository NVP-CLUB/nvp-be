package nvp_api.auth.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.auth.application.service.AuthService;
import nvp_api.common.jwt.TokenDTO;
import nvp_api.common.response.ApiResponse;
import nvp_api.auth.application.dto.LoginMemberDTO;
import nvp_api.security.SecurityUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 일반 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenDTO>> loginMember(@RequestBody @Valid LoginMemberDTO loginMemberDTO){

        TokenDTO tokenDTO = authService.memberLogin(loginMemberDTO);

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + tokenDTO.getAccessToken());

        // Refresh Token을 쿠키로 설정 (보안 강화)
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", tokenDTO.getRefreshToken())
                .httpOnly(true) // JavaScript에서 접근 불가 (XSS 방어)
                .secure(false)   // HTTPS에서만 전송 (true / false)
                .path("/")
                .maxAge(60 * 60 * 24 * 7) // 7일 유지
                .build();
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        // 클라이언트에 Access Token만 반환 (Refresh Token은 포함하지 않음)
        TokenDTO responseTokenDTO = TokenDTO.builder()
                .grantType(tokenDTO.getGrantType())
                .accessToken(tokenDTO.getAccessToken())
                .accessTokenExpiresIn(tokenDTO.getAccessTokenExpiresIn())
                .userRole(tokenDTO.getUserRole())
                .userNo(tokenDTO.getUserNo())
                .build();

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Success", responseTokenDTO));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logoutMember(@RequestHeader(name = "Authorization") String accessToken){

        authService.memberLogout(SecurityUtil.getCurrentUserId(), accessToken);

        return ApiResponse.success(SecurityUtil.getCurrentUserId());
    }

}
