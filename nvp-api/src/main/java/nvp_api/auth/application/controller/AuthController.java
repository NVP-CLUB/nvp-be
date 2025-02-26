package nvp_api.auth.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.common.response.ApiResponse;
import nvp_api.auth.application.dto.LoginMemberDTO;
import nvp_api.auth.application.dto.RegisterMemberDTO;
import nvp_api.member.application.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    // 일반 회원가입
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerMember(@RequestBody @Valid RegisterMemberDTO registerMemberDTO){

        String userId = memberService.registerMemberUser(registerMemberDTO);

        return ApiResponse.create(userId);
    }

    // 일반 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> loginMember(@RequestBody @Valid LoginMemberDTO loginMemberDTO){


    }
}
