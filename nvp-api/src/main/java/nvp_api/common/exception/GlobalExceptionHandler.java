package nvp_api.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import nvp_api.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 존재하지 않는 요청에 대한 예외 (404)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NoHandlerFoundException ex) {
        log.error("[ERROR - NoHandlerFoundException] {}", ex.getMessage());
        return ApiResponse.error(HttpStatus.NOT_FOUND.value(), "요청한 페이지를 찾을 수 없습니다.");
    }

    // 지원하지 않는 Method 요청에 대한 예외 (405)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("[ERROR - HttpRequestMethodNotSupportedException] {}", ex.getMessage());
        return ApiResponse.error(HttpStatus.METHOD_NOT_ALLOWED.value(), "해당 요청 방식(Method)은 지원되지 않습니다.");
    }

    // 상정하지 못한 예외 (500)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        log.error("[ERROR - EXCEPTION] exceptionInfo: {}, message: {}", ex, ex.getMessage());
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부 오류가 발생 했습니다.");
    }

    // 사용자 정의 예외 (Custom)
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
        log.error("[ERROR - CUSTOM] ErrorCode: {}, message: {}", ex.getErrorCode().getCode(), ex.getErrorCode().getMessage());
        return ApiResponse.error(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage());
    }
}
