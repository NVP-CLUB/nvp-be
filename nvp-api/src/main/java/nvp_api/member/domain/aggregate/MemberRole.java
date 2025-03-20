package nvp_api.member.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nvp_api.role.domain.aggregate.Role;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "member_roles")
public class MemberRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberRoleNo;        // 사용자 권한 고유 번호

    @ManyToOne
    @JoinColumn(name = "role_no", nullable = false)
    private Role role;              // 해당 권한

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private MemberUser memberUser;  // 사용자

    public MemberRole(Role role, MemberUser memberUser) {
        this.role = role;
        this.memberUser = memberUser;
    }
}
