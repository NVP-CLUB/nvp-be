package nvp_api.auth.application.dto;

import lombok.Getter;
import lombok.Setter;
import nvp_api.role.domain.aggregate.Role;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private List<Role> role;
    private String name;
    private String username;
    private Long userNo;
}
