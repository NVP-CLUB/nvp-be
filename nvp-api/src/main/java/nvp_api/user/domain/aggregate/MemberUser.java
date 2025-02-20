package nvp_api.user.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

/**
 *  사용자 로그인 관련 엔티티
 */

@Getter
@Entity
@Table(name = "member_users")
public class MemberUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;                        // 고유 번호

    @Column(nullable = false, unique = true)
    private String userId;                      // 사용자 아이디

    @Column(nullable = false)
    private String loginType;                   // 로그인 타입

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;          // 아이디 활동 상태

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = UserStatus.ACTIVE;
        }
    }

    // 활동 상태
    public enum UserStatus {
        ACTIVE,    // 활동중
        DELETED,   // 탈퇴됨
        SUSPENDED  // 정지됨
    }
}
