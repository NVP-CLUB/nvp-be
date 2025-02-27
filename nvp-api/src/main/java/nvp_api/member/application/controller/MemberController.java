package nvp_api.member.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.auth.application.dto.RegisterMemberDTO;
import nvp_api.common.response.ApiResponse;
import nvp_api.member.application.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 일반 회원가입
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerMember(@RequestBody @Valid RegisterMemberDTO registerMemberDTO){

        String userId = memberService.registerMemberUser(registerMemberDTO);

        return ApiResponse.create(userId);
    }
}
