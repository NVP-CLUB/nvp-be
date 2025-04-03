package nvp_api.auth.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvp_api.auth.application.dto.CustomOAuth2User;
import nvp_api.auth.application.dto.KakaoResponse;
import nvp_api.auth.application.dto.OAuth2Response;
import nvp_api.auth.application.dto.UserDTO;
import nvp_api.auth.domain.aggregate.MemberAuthentication;
import nvp_api.auth.domain.repository.MemberAuthenticationRepository;
import nvp_api.common.exception.CustomException;
import nvp_api.common.exception.ErrorCode;
import nvp_api.common.util.DateTimeUtil;
import nvp_api.member.domain.aggregate.MemberProfile;
import nvp_api.member.domain.aggregate.MemberRole;
import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.member.domain.repository.MemberProfileRepository;
import nvp_api.member.domain.repository.MemberRoleRepository;
import nvp_api.member.domain.repository.MemberUserRepository;
import nvp_api.role.domain.aggregate.Role;
import nvp_api.role.domain.repository.RoleRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberUserRepository memberUserRepository;
    private final MemberAuthenticationRepository memberAuthenticationRepository;
    private final MemberProfileRepository memberProfileRepository;

    private final MemberRoleRepository memberRoleRepository;
    private final RoleRepository roleRepository;

    private static final String guest = "GUEST";

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("oAuth2User: {}", oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        // 인증 식별 서버
        if (registrationId.equals("kakao")){

            log.info("registrationId: kakao");
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {

        } else {
            return null;
        }

        String username = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();
        Optional<MemberUser> byUsername = memberUserRepository.findByUserId(username);

        if (byUsername.isEmpty()) {

            log.info("loginType: {}", registrationId);
            log.info("username: {}", username);
            log.info("getProvider: {}", oAuth2Response.getProvider());
            log.info("getProviderId: {}", oAuth2Response.getProviderId());
            log.info("getEmail: {}", oAuth2Response.getEmail());

            // 사용자 생성
            MemberUser memberUserSave = memberUserRepository.save(new MemberUser(username, registrationId, oAuth2Response.getProviderId(), oAuth2Response.getEmail()));

            // 사용자 정보 생성
            memberAuthenticationRepository.save(new MemberAuthentication(DateTimeUtil.toLocalDate(oAuth2Response.getBirthDate()), oAuth2Response.getName(), memberUserSave, oAuth2Response.getGender().equals("male")));

            // 사용자 프로필 생성
            memberProfileRepository.save(new MemberProfile(memberUserSave));

            // 역할 꺼내기 및 없으면 새로 저장
            Role guestRole = roleRepository.findByRoleName(guest)
                    .orElseGet(() -> roleRepository.save(new Role(guest)));

            // 사용자 역할 저장 (기본 게스트)
            memberRoleRepository.save(new MemberRole(guestRole, memberUserSave));

            // UserDTO 생성
            UserDTO userDTO = new UserDTO();
            userDTO.setUserNo(memberUserSave.getUserNo());
            userDTO.setUsername(username);
            userDTO.setName(oAuth2User.getName());
            userDTO.setRole(List.of(guestRole));

            return new CustomOAuth2User(userDTO);

        } else {

            MemberUser memberUser = byUsername.get();

            // 정보 변경 시를 대비한 업데이트
            memberUser.socialUpdateData(oAuth2Response.getEmail());
            memberUserRepository.save(memberUser);

            // MemberAuthentication 찾기
            MemberAuthentication memberAuthentication = memberAuthenticationRepository.findByMemberUser(memberUser)
                    .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_AUTHENTICATION_NOT_FOUND));

            // 업데이트
            memberAuthentication.socialUpdateMemberAuth(
                    DateTimeUtil.toLocalDate(oAuth2Response.getBirthDate()),
                    oAuth2Response.getName(),
                    oAuth2Response.getGender().equals("MALE")
            );
            memberAuthenticationRepository.save(memberAuthentication);

            List<MemberRole> allByMemberUser = memberRoleRepository.findAllByMemberUser(memberUser);

            List<Role> allRole = allByMemberUser.stream().map(MemberRole::getRole).toList();

            // UserDTO 생성
            UserDTO userDTO = new UserDTO();
            userDTO.setUserNo(memberUser.getUserNo());
            userDTO.setUsername(username);
            userDTO.setName(oAuth2User.getName());
            userDTO.setRole(allRole);

            return new CustomOAuth2User(userDTO);
        }

    }
}
