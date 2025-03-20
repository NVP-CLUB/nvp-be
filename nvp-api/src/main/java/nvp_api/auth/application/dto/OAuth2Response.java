package nvp_api.auth.application.dto;

public interface OAuth2Response {

    // 제공자 (KAKAO, GOOGLE)
    String getProvider();

    // 제공자에서 발급되는 아이디
    String getProviderId();

    // 이메일
    String getEmail();

    // 이름
    String getName();

    // 생년월일
    String getBirthDate();

    // 성별
    String getGender();

}
