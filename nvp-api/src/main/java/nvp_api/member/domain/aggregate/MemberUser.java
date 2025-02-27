package nvp_api.member.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  사용자 로그인 관련 엔티티
 */

@Getter
@Entity
@Table(name = "member_users")
@NoArgsConstructor
public class MemberUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;                        // 고유 번호

    @Column(nullable = false, unique = true)
    private String userId;                      // 사용자 아이디

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType loginType;                   // 로그인 타입

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;          // 아이디 활동 상태

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = UserStatus.ACTIVE;
        }

        if (loginType == null) {
            loginType = LoginType.EMAIL;
        }
    }

    // 로그인 타입
    public enum LoginType {
        KAKAO,     // 카카오 로그인
        GOOGLE,    // 구글 로그인
        EMAIL      // 일반 로그인 (이메일)
    }

    // 활동 상태
    public enum UserStatus {
        ACTIVE,    // 활동중
        DELETED,   // 탈퇴됨
        SUSPENDED  // 정지됨
    }

    // 이메일 가입 생성자
    public MemberUser(String userId, LoginType loginType) {
        this.userId = userId;
        this.loginType = loginType;
    }

}
