package nvp_api.role.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "role_no")
    private Long roleNo;        // 역할 고유 번호

    @Column(nullable = false, name = "role_name")
    private String roleName;        // 역할 명 (USER, ADMIN, MANAGER ...)

    // 생성자
    public Role(String roleName) {
        this.roleName = roleName;
    }

    // 사용자 권한
    public enum RoleType {
        GUEST,      // 게스트 (회원가입후 부원인증 X)
        USER,       // 회원 (회원가입후 부원인증 O)
        MANAGER,    // 매니저 (직무 => 매니저)
        SYSTEM,     // 시스템 관리자
        ADMIN       // 어드민
    }

}
