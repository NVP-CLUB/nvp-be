package nvp_api.auth.domain.repository;

import nvp_api.auth.domain.aggregate.AuthPassword;
import nvp_api.member.domain.aggregate.MemberUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthPasswordRepository {

    /**
     * ======================= 조회 ======================= //
     */

    // 전체 조회
    List<AuthPassword> findAll();

    // 비밀번호 고유 번호로 조회
    Optional<AuthPassword> findByPasswordId(long id);

    // 사용자 고유 번호로 조회
    Optional<AuthPassword> findByMemberUser(MemberUser user);

    // 업데이트 날짜로 조회
    List<AuthPassword> findAllByUpdatedAt(LocalDateTime updatedAt);

    /**
     * ======================= 삭제 ======================= //
     */

    // 삭제
    void delete(AuthPassword authPassword);

}
