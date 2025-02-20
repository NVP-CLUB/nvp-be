package nvp_api.user.domain.aggregate;

import jakarta.persistence.*;

/**
 *  사용자 프로필 엔티티
 */

@Entity
@Table(name = "member_profiles")
public class MemberProfile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long profileId;                     // 고유 번호

    @Column(nullable = true)
    private String imageUrl;                    // 프로필 사진

    @Column(nullable = true)
    private Integer memberNo;                       // 등번호 (nullable이기에 Integer 사용)

    @OneToOne
    @JoinColumn(name = "user_no")
    private MemberUser memberUser;              // 사용자
}
