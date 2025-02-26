package nvp_api.auth.domain.repository;

import nvp_api.auth.domain.aggregate.AuthPassword;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthPasswordRepository {

    /**
     * ======================= 저장 ======================= //
     */

    // 저장
    Optional<AuthPassword> save(AuthPassword authPassword);

    // 전체 저장
    List<AuthPassword> saveAll(List<AuthPassword> authPasswords);

    /**
     * ======================= 조회 ======================= //
     */

    // 전체 조회
    List<AuthPassword> findAll();

    // 비밀번호 고유 번호로 조회
    Optional<AuthPassword> findByPasswordId(long id);

    // 사용자 고유 번호로 조회
    Optional<AuthPassword> findByUserNo(long userNo);

    // 업데이트 날짜로 조회
    List<AuthPassword> findAllByUpdated_at(LocalDateTime updatedAt);

    /**
     * ======================= 삭제 ======================= //
     */

    // 삭제
    void delete(AuthPassword authPassword);

    // 전체 삭제
    void deleteAll(List<AuthPassword> authPasswords);

}
