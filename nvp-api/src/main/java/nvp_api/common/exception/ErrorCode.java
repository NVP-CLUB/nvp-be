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
    // 사용자 (User)
    NEED_LOGIN(40100, HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다.");


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

    /**
     *  INTERNAL_SERVER_ERROR 500xx
     *  -> 서버 오류
     */

    private final int code;                 // 서비스 자체 오류 코드
    private final HttpStatus httpStatus;    // 해당 HttpStatus
    private final String message;           // 오류 메세지
}
