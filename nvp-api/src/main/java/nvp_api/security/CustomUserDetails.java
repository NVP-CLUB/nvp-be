package nvp_api.security;

import lombok.RequiredArgsConstructor;
import nvp_api.auth.domain.aggregate.AuthPassword;
import nvp_api.member.domain.aggregate.MemberRole;
import nvp_api.member.domain.aggregate.MemberUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final MemberUser memberUser;
    private final AuthPassword authPassword;
    private final List<MemberRole> userRoles;

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한 리스트 생성
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // 사용자가 가지고 있는 권한 SimpleGrantedAuthority로 변환 후 추가
        // SimpleGrantedAuthority는 시큐리티에서 권한을 나타내는 기본 클래스
        // 접두사 "ROLE_" 추가 (시큐리티에서 권한을 구분하기 위한 규칙) hasRole에서 "ADMIN"만 써도 인식 가능
        for (MemberRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole().getRoleName()));
        }

        return authorities;
    }

    // 계정 비밀번호 반환
    @Override
    public String getPassword() {
        return authPassword.getPassword();
    }

    // 계정 아이디 반환
    @Override
    public String getUsername() {
        return memberUser.getUserId();
    }

    // 계정 고유 번호 반환
    public Long getUserNo(){
        return memberUser.getUserNo();
    }

}
