package nvp_api.auth.application.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;
    private final Map<String, Object> kakaoAccount;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
        this.kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
    }

    // 식별 서버
    @Override
    public String getProvider() {
        return "kakao";
    }

    // 인증 고유 식별 ID
    @Override
    public String getProviderId() {
        return String.valueOf(attribute.get("id"));
    }

    // 사용자 이메일
    @Override
    public String getEmail() {

        return (String) kakaoAccount.get("email");
    }

    // 사용자 이름
    @Override
    public String getName() {

        return (String) kakaoAccount.get("name");
    }

    // 사용자 생년월일 (ex. 2025-11-30)
    @Override
    public String getBirthDate(){

        return (String) kakaoAccount.get("birthyear") + (String) kakaoAccount.get("birthday");
    }

    // 사용자 성별
    @Override
    public String getGender(){

        return (String) kakaoAccount.get("gender");
    }
}
