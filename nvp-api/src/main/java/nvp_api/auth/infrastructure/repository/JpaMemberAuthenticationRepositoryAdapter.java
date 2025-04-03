package nvp_api.auth.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import nvp_api.auth.domain.aggregate.MemberAuthentication;
import nvp_api.auth.domain.repository.MemberAuthenticationRepository;
import nvp_api.member.domain.aggregate.MemberUser;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberAuthenticationRepositoryAdapter implements MemberAuthenticationRepository {

    private final JpaMemberAuthenticationRepository jpaMemberAuthenticationRepository;

    @Override
    public List<MemberAuthentication> findAll() {
        return jpaMemberAuthenticationRepository.findAll();
    }

    @Override
    public Optional<MemberAuthentication> findByAuthenticationId(long authenticationId) {
        return jpaMemberAuthenticationRepository.findByAuthenticationId(authenticationId);
    }

    @Override
    public List<MemberAuthentication> findAllByIsPublic(boolean istPublic) {
        return jpaMemberAuthenticationRepository.findAllByIsPublic(istPublic);
    }

    @Override
    public List<MemberAuthentication> findAllByBirthday(LocalDate birthday) {
        return jpaMemberAuthenticationRepository.findAllByBirthday(birthday);
    }

    @Override
    public List<MemberAuthentication> findAllByName(String name) {
        return jpaMemberAuthenticationRepository.findAllByName(name);
    }

    @Override
    public Optional<MemberAuthentication> findByMemberUser(MemberUser memberUser) {
        return jpaMemberAuthenticationRepository.findByMemberUser(memberUser);
    }

    @Override
    public MemberAuthentication save(MemberAuthentication memberAuthentication) {
        return jpaMemberAuthenticationRepository.save(memberAuthentication);
    }

    @Override
    public List<MemberAuthentication> saveAll(List<MemberAuthentication> memberAuthentications) {
        return jpaMemberAuthenticationRepository.saveAll(memberAuthentications);
    }

    @Override
    public void deleteByAuthenticationId(long authenticationId) {
        jpaMemberAuthenticationRepository.deleteByAuthenticationId(authenticationId);
    }

    @Override
    public void delete(MemberAuthentication memberAuthentication) {
        jpaMemberAuthenticationRepository.delete(memberAuthentication);
    }

    @Override
    public void deleteAll(List<MemberAuthentication> memberAuthentications) {
        jpaMemberAuthenticationRepository.deleteAll(memberAuthentications);
    }
}
