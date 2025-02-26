package nvp_api.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     *  BAD_REQUEST 400xx
     *  -> 잘못된 요청
     */

    /**
     *  UNAUTHORIZED 401xx
     *  -> 인증되지 않음
     */
    // 사용자 (Member)
    NEED_LOGIN(40100, HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다."),


    /**
     *  FORBIDDEN 403xx
     *  -> 인가 오류 (권한 없음)
     */

    /**
     *  NOT_FOUND 404xx
     *  -> 존재하지 않음
     */

    /**
     *  CONFLICT 409xx
     *  -> 중복된 사항
     */
    // 사용자 (Member)
    CONFLICT_USERID_EMAIL(40900, HttpStatus.CONFLICT, "이미 가입된 아이디입니다."),
    CONFLICT_USERID_KAKAO(40901, HttpStatus.CONFLICT, "이미 카카오로 가입된 이메일입니다"),
    CONFLICT_USERID_GOOGLE(40902, HttpStatus.CONFLICT, "이미 구글로 가입된 이메일입니다."),

    /**
     *  INTERNAL_SERVER_ERROR 500xx
     *  -> 서버 오류
     */
    // 사용자
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "가입 중 오류가 발생했습니다.");

    private final int code;                 // 서비스 자체 오류 코드
    private final HttpStatus httpStatus;    // 해당 HttpStatus
    private final String message;           // 오류 메세지
}
