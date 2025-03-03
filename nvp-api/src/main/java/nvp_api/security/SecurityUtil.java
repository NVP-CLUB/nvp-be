package nvp_api.security;

import lombok.extern.slf4j.Slf4j;
import nvp_api.common.jwt.TokenSaveDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    private static TokenSaveDTO getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("SecurityUtil.getCurrentMemberId 호출 {}", authentication.getPrincipal().toString());

        if (authentication.getPrincipal() == null || authentication.getName() == null ||
                !(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new RuntimeException("Security Context 에 인증 정보가 없음");
        }

        return (TokenSaveDTO) authentication.getPrincipal();
    }

    // 사용자 코드 반환
    public static Long getCurrentUserNo(){

        return getAuthentication().getUserNo();
    }

    // 사용자 아이디 반환
    public static String getCurrentUserId(){

        return getAuthentication().getUserId();
    }

    // 액세스 토큰 반환
    public static String getCurrentAccessToken(){

        return getAuthentication().getAccessToken();
    }

}
