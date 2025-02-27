package nvp_api.auth.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.auth.application.service.AuthService;
import nvp_api.common.jwt.TokenDTO;
import nvp_api.common.response.ApiResponse;
import nvp_api.auth.application.dto.LoginMemberDTO;
import nvp_api.auth.application.dto.RegisterMemberDTO;
import nvp_api.member.application.service.MemberService;
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

        return ApiResponse.success(authService.memberLogin(loginMemberDTO));
    }
}
