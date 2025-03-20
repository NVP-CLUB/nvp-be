package nvp_api.auth.application.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    // 식별 서버
    @Override
    public String getProvider() {
        return "kakao";
    }

    // 인증 고유 식별 ID
    @Override
    public String getProviderId() {
        return (String) attribute.get("sub");
    }

    // 사용자 이메일
    @Override
    public String getEmail() {

        return (String) attribute.get("email");
    }

    // 사용자 이름
    @Override
    public String getName() {

        return (String) attribute.get("name");
    }

    // 사용자 생년월일 (ex. 2025-11-30)
    @Override
    public String getBirthDate(){

        return (String) attribute.get("birthdate");
    }

    // 사용자 성별
    @Override
    public String getGender(){

        return (String) attribute.get("gender");
    }
}
