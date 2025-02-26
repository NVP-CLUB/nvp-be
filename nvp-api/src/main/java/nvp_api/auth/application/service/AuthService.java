package nvp_api.auth.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.auth.application.dto.LoginMemberDTO;
import nvp_api.common.jwt.TokenDTO;
import nvp_api.common.jwt.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    // 일반 로그인
    public void memberLogin(LoginMemberDTO loginMemberDTO){

        // 아이디 및 비밀번호 기반 Authentication 생성
        UsernamePasswordAuthenticationToken authentication = loginMemberDTO.toAuthentication();

        // 인증 관리자를 통해 인증을 진행 (loadUserByUsername 작동)
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authentication);

        // 인증 정보 기반 JWT 생성
        TokenDTO tokenDTO = tokenProvider.generateToken(authenticate);
    }
}
