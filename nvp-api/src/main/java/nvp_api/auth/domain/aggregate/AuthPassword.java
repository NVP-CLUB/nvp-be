package nvp_api.auth.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nvp_api.common.aggregate.entity.UpdateTimeEntity;
import nvp_api.member.domain.aggregate.MemberUser;

/**
 *  인증 비밀번호 엔티티
 */

@Getter
@Entity
@Table(name = "auth_passwords")
@NoArgsConstructor
public class AuthPassword extends UpdateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordId;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_no")
    private MemberUser memberUser;

    // 이메일 가입 생성자
    public AuthPassword(MemberUser memberUser, String password) {
        this.memberUser = memberUser;
        this.password = password;
    }
}
