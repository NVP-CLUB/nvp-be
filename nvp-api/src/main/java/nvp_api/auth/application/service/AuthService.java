package nvp_api.auth.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.auth.application.dto.LoginMemberDTO;
import nvp_api.auth.domain.aggregate.RefreshToken;
import nvp_api.auth.infrastructure.repository.CrudRefreshTokenRepository;
import nvp_api.common.jwt.TokenDTO;
import nvp_api.common.jwt.TokenProvider;
import nvp_api.auth.domain.aggregate.BlackList;
import nvp_api.auth.infrastructure.repository.CrudBlackListRepository;
import nvp_api.security.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final CrudBlackListRepository blackListRepository;
    private final CrudRefreshTokenRepository refreshTokenRepository;

    // 일반 로그인
    public TokenDTO memberLogin(LoginMemberDTO loginMemberDTO){

        // 아이디 및 비밀번호 기반 Authentication 생성
        UsernamePasswordAuthenticationToken authentication = loginMemberDTO.toAuthentication();

        // 인증 관리자를 통해 인증을 진행 (loadUserByUsername 작동)
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authentication);

        // 인증 정보 기반 JWT 생성
        TokenDTO tokenDTO = tokenProvider.generateToken(authenticate);

        // 사용자 역할 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        List<String> userRole = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // RefreshToken 저장소에 저장
        refreshTokenRepository.save(
                RefreshToken.builder()
                .refreshToken(tokenDTO.getRefreshToken())
                .userId(loginMemberDTO.getUserId())
                .build()
        );

        tokenDTO.setUserRole(userRole);

        return tokenDTO;
    }

    // 로그아웃
    public void memberLogout(String userId, String accessToken){
        BlackList build = BlackList.builder()
                .accessToken(accessToken)
                .userId(userId)
                .build();

        blackListRepository.save(build);
    }
}
