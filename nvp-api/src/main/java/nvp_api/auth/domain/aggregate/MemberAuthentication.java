package nvp_api.auth.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nvp_api.member.domain.aggregate.MemberUser;

import java.time.LocalDate;

/**
 * 사용자 개인정보 엔티티
 */

@Getter
@Entity
@Table(name = "member_authentications")
@NoArgsConstructor
public class MemberAuthentication {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long authenticationId;                      // 고유 번호

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isPublic;                           // 활동 공개 여부 0(비공개) 1(기본값, 공개)

    @Column(nullable = false)
    private LocalDate birthday;                         // 생일
    
    @Column(nullable = false)
    private String name;                                // 이름

    @OneToOne
    @JoinColumn(nullable = false, name = "user_no")
    private MemberUser memberUser;

    @PrePersist
    public void prePersist() {
        // 활동 공개 여부 초기값 세팅
        if (isPublic == null) {
            isPublic = true;
        }
    }

    // 이메일 가입 생성자
    public MemberAuthentication(Boolean isPublic, LocalDate birthday, String name, MemberUser memberUser) {
        this.isPublic = isPublic;
        this.birthday = birthday;
        this.name = name;
        this.memberUser = memberUser;
    }
}
