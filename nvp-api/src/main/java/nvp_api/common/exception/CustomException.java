package nvp_api.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    // 사용자 지정 예외처리 Exception

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
