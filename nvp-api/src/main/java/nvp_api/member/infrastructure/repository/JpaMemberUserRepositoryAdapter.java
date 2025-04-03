package nvp_api.member.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.member.domain.repository.MemberUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberUserRepositoryAdapter implements MemberUserRepository {

    private final JpaMemberUserRepository jpaMemberUserRepository;

    @Override
    public List<MemberUser> findAll() {
        return jpaMemberUserRepository.findAll();
    }

    @Override
    public Optional<MemberUser> findByUserId(String userId) {
        return jpaMemberUserRepository.findByUserId(userId);
    }

    @Override
    public List<MemberUser> findByLoginType(String loginType) {
        return jpaMemberUserRepository.findByLoginType(loginType);
    }

    @Override
    public MemberUser save(MemberUser memberUser) {
        return jpaMemberUserRepository.save(memberUser);
    }

    @Override
    public List<MemberUser> saveAll(List<MemberUser> memberUsers) {
        return jpaMemberUserRepository.saveAll(memberUsers);
    }

    @Override
    public void delete(MemberUser memberUser) {
        jpaMemberUserRepository.delete(memberUser);
    }

    @Override
    public void deleteAll(List<MemberUser> memberUsers) {
        jpaMemberUserRepository.deleteAll(memberUsers);
    }
}
