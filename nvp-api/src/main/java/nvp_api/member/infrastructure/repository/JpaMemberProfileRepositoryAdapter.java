package nvp_api.member.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import nvp_api.member.domain.aggregate.MemberProfile;
import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.member.domain.repository.MemberProfileRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberProfileRepositoryAdapter implements MemberProfileRepository {

    private final JpaMemberProfileRepository jpaMemberProfileRepository;

    @Override
    public List<MemberProfile> findAll() {
        return jpaMemberProfileRepository.findAll();
    }

    @Override
    public Optional<MemberProfile> findByProfileId(long id) {
        return jpaMemberProfileRepository.findByProfileId(id);
    }

    @Override
    public Optional<MemberProfile> findByMemberUser(MemberUser memberUser) {
        return jpaMemberProfileRepository.findByMemberUser(memberUser);
    }

    @Override
    public List<MemberProfile> findAllByMemberNo(int memberNo) {
        return jpaMemberProfileRepository.findAllByMemberNo(memberNo);
    }

    @Override
    public List<MemberProfile> findAllByCreatedAt(LocalDateTime registerDate) {
        return jpaMemberProfileRepository.findAllByCreatedAt(registerDate);
    }

    @Override
    public MemberProfile save(MemberProfile memberProfile) {
        return jpaMemberProfileRepository.save(memberProfile);
    }

    @Override
    public List<MemberProfile> saveAll(List<MemberProfile> memberProfiles) {
        return jpaMemberProfileRepository.saveAll(memberProfiles);
    }

    @Override
    public void delete(MemberProfile memberProfile) {
        jpaMemberProfileRepository.delete(memberProfile);
    }

    @Override
    public void deleteAll(List<MemberProfile> memberProfiles) {
        jpaMemberProfileRepository.deleteAll(memberProfiles);
    }
}
