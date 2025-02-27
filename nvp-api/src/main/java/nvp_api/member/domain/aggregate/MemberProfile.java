package nvp_api.member.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 *  사용자 프로필 엔티티
 */

@Entity
@Table(name = "member_profiles")
@NoArgsConstructor
public class MemberProfile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long profileId;                     // 고유 번호

    @Column(nullable = true)
    private String imageUrl;                    // 프로필 사진 (추후에 타입 변경)

    @Column(nullable = true)
    private Integer memberNo;                       // 등번호 (nullable이기에 Integer 사용)

    @OneToOne
    @JoinColumn(name = "user_no")
    private MemberUser memberUser;              // 사용자

    // 이메일 가입 생성자 (추후에 사진 주소 추가)
    public MemberProfile(MemberUser memberUser) {
        this.memberUser = memberUser;
    }
}
