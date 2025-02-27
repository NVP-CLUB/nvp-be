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
    EXPIRED_TOKEN(40101, HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    INVALID_TOKEN(40102, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    // JWT 관련 (Token)
    INVALID_JWT_SIGNATURE(40110, HttpStatus.UNAUTHORIZED, "잘못된 JWT 서명입니다."),
    MALFORMED_JWT_TOKEN(40111, HttpStatus.UNAUTHORIZED, "잘못된 JWT 토큰 형식입니다."),
    UNSUPPORTED_JWT_TOKEN(40112, HttpStatus.BAD_REQUEST, "지원되지 않는 JWT 토큰입니다."),
    EMPTY_JWT_CLAIMS(40113, HttpStatus.BAD_REQUEST, "JWT 클레임이 없습니다."),

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
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "가입 중 오류가 발생했습니다.");

    private final int code;                 // 서비스 자체 오류 코드
    private final HttpStatus httpStatus;    // 해당 HttpStatus
    private final String message;           // 오류 메시지
}
