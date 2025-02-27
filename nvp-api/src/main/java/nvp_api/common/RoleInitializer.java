package nvp_api.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.role.domain.aggregate.Role;
import nvp_api.role.infrastructure.repository.JpaRoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleInitializer implements ApplicationRunner {

    private final JpaRoleRepository roleRepository;

    // 역할 생성기
    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 사용자 권한 Enum
        for (Role.RoleType roleType : Role.RoleType.values()) {
            // 해당 역할이 있는지 확인
            roleRepository.findByName(roleType.name()).ifPresentOrElse(
            role -> {
                log.info("Role found: {}", role.getRoleName() + "존재하는 권한");
            },
            () -> {
                // 역할 존재하지 않으면 추가
                Role role = new Role(roleType.name());
                roleRepository.save(role);
                log.info("Role {} 생성", roleType.name());
            });
        }
    }


}
