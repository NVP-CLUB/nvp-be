package nvp_api.member.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.member.application.dto.RegisterMemberDTO;
import nvp_api.auth.domain.aggregate.AuthPassword;
import nvp_api.auth.domain.aggregate.MemberAuthentication;
import nvp_api.auth.infrastructure.repository.JpaAuthPasswordRepository;
import nvp_api.auth.infrastructure.repository.JpaMemberAuthenticationRepository;
import nvp_api.common.exception.CustomException;
import nvp_api.common.exception.ErrorCode;
import nvp_api.member.domain.aggregate.MemberProfile;
import nvp_api.member.domain.aggregate.MemberRole;
import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.member.infrastructure.repository.JpaMemberProfileRepository;
import nvp_api.member.infrastructure.repository.JpaMemberRoleRepository;
import nvp_api.member.infrastructure.repository.JpaMemberUserRepository;
import nvp_api.role.domain.aggregate.Role;
import nvp_api.role.infrastructure.repository.JpaRoleRepository;
import nvp_api.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final JpaAuthPasswordRepository authPasswordRepository;
    private final JpaMemberAuthenticationRepository memberAuthenticationRepository;
    private final JpaMemberProfileRepository memberProfileRepository;
    private final JpaMemberUserRepository memberUserRepository;

    private final JpaMemberRoleRepository memberRoleRepository;
    private final JpaRoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // CustomUserDetailsService에서의 SRP를 지키기 위해 UserService로 분리
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
        // 사용자 가져오기
        MemberUser memberUser = memberUserRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId));

        if (memberUser.getStatus().equals(MemberUser.UserStatus.DELETED)){
            // 탈퇴된 회원인 경우
            throw new CustomException(ErrorCode.DELETED_USER);

        } else if (memberUser.getStatus().equals(MemberUser.UserStatus.SUSPENDED)){
            // 정지된 회원인 경우
            throw new CustomException(ErrorCode.SUSPENDED_USER);
        }

        // 비밀번호 가져오기
        AuthPassword authPassword = authPasswordRepository.findByMemberUser(memberUser)
                .orElseThrow(() -> new UsernameNotFoundException(userId));

        // 권한 가져오기
        List<MemberRole> allByUserNo = memberRoleRepository.findAllByMemberUser(memberUser);

        return new CustomUserDetails(memberUser, authPassword, allByUserNo);
    }

    // 일반 회원가입
    @Transactional
    public String registerMemberUser(RegisterMemberDTO registerMemberDTO){

        // 해당 이메일로 가입된 정보 확인
        memberUserRepository.findByUserId(registerMemberDTO.getUserId())
                .ifPresent(user -> {
                    if (user.getLoginType().equals("kakao")){
                        // 카카오 중복 가입
                        throw new CustomException(ErrorCode.CONFLICT_USERID_KAKAO);
                    } else if (user.getLoginType().equals("google")) {
                        // 구글 중복 가입
                        throw new CustomException(ErrorCode.CONFLICT_USERID_GOOGLE);
                    } else {
                        // 일반 회원 중복 가입
                        throw new CustomException(ErrorCode.CONFLICT_USERID_EMAIL);
                    }
                });

        // 가입 정보 저장
        MemberUser save = memberUserRepository.save(new MemberUser(registerMemberDTO.getUserId()));

        // 비밀번호 저장
        String encode = bCryptPasswordEncoder.encode(registerMemberDTO.getPassword());
        authPasswordRepository.save(new AuthPassword(save, encode));

        // 사용자 정보 저장
        memberAuthenticationRepository.save(
                new MemberAuthentication(
                        registerMemberDTO.getBirthday(), registerMemberDTO.getName(), save, registerMemberDTO.isMale()
                )
        );

        // 사용자 프로필 저장
        memberProfileRepository.save(new MemberProfile(save));

        // 역할 꺼내기 및 없으면 새로 저장
        Role guestRole = roleRepository.findByRoleName("GUEST")
                .orElseGet(() -> roleRepository.save(new Role("GUEST")));

        // 사용자 역할 저장 (기본 게스트)
        memberRoleRepository.save(new MemberRole(guestRole, save));

        return save.getUserId();
    }

}
