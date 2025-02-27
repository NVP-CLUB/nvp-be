package nvp_api.auth.application.dto;

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

    private String userId;

    private String password;

    private LocalDate birthday;

    private String name;

}
