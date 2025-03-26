package nvp_api.auth.application.dto;

import nvp_api.role.domain.aggregate.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;

    public CustomOAuth2User(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한 리스트 생성
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // 사용자가 가지고 있는 권한 SimpleGrantedAuthority로 변환 후 추가
        // SimpleGrantedAuthority는 시큐리티에서 권한을 나타내는 기본 클래스
        // 접두사 "ROLE_" 추가 (시큐리티에서 권한을 구분하기 위한 규칙) hasRole에서 "ADMIN"만 써도 인식 가능
        for (Role userRole : userDTO.getRole()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole));
        }

        return authorities;
    }

    @Override
    public String getName() {
        return userDTO.getName();
    }

    // provider + "_" + providerId
    public String getUsername(){
        return userDTO.getUsername();
    }

    // userNo
    public Long getUserNo(){
        return userDTO.getUserNo();
    }
}
