package nvp_api.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T>{

    private final int status;   // Http 상태코드
    private final String message;   // 응답 메시지
    private final T data;       // 실제 데이터

    // 성공시 데이터와 함께 보내는 반환값
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Success", data));
    }

    // 새로 가입되거나 작성되었을때 반환값
    public static <T> ResponseEntity<ApiResponse<T>> create(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created", data));
    }

    // 에러가 발생 했을때 반환값
    public static <T>ResponseEntity<ApiResponse<T>> error(int status, String message) {
        return ResponseEntity.status(status)
                .body(new ApiResponse<>(status, message, null));
    }
}
