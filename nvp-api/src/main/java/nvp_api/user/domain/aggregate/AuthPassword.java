package nvp_api.user.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

/**
 *  인증 비밀번호 엔티티
 */

@Getter
@Entity
@Table(name = "auth_passwords")
public class AuthPassword {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordId;

    @Column(nullable = false)
    private String password;

}
