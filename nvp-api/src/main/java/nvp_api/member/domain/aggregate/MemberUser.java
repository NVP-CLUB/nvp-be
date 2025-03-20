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
    private String loginType;                   // 로그인 타입

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;          // 아이디 활동 상태

    @Column
    private String providerId;                      // 인증 고유 번호

    @Column
    private String email;                        // 인증 서버 + 인증 고유 번호

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = UserStatus.ACTIVE;
        }

        if (loginType == null) {
            loginType = "email";
        }
    }

    // 로그인 타입
//    public enum LoginType {
//        KAKAO,     // 카카오 로그인
//        GOOGLE,    // 구글 로그인
//        EMAIL      // 일반 로그인 (이메일)
//    }

    // 활동 상태
    public enum UserStatus {
        ACTIVE,    // 활동중
        DELETED,   // 탈퇴됨
        SUSPENDED  // 정지됨
    }

    // 이메일 가입 생성자
    public MemberUser(String userId) {
        this.userId = userId;
        this.loginType = "email";
    }

    // 소셜 계정 생성
    public MemberUser(String userId, String loginType, String probiderId, String email){
        this.userId = userId;
        this.loginType = loginType;
        this.providerId = probiderId;
        this.email = email;
    }

    // 소셜 로그인시 정보 업데이트
    public void socialUpdateData(String email) {
        this.email = email;
    }

}
