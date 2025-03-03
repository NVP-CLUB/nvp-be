package nvp_api.member.application.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RegisterMemberDTO {

    /**
     * 일반 회원가입 DTO
     */

    @Email
    private String userId;          // 사용자 아이디 (이메일)

    private String password;        // 비밀번호

    private LocalDate birthday;     // 생년월일

    private String name;     // 이름

    private boolean isMale; // 1 남자, 0 여자

}
