package nvp_api.member.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import nvp_api.member.domain.aggregate.MemberRole;
import nvp_api.member.domain.aggregate.MemberUser;
import nvp_api.member.domain.repository.MemberRoleRepository;
import nvp_api.role.domain.aggregate.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberRoleRepositoryAdapter implements MemberRoleRepository {

    private final JpaMemberRoleRepository jpaMemberRoleRepository;

    @Override
    public Optional<MemberRole> findByMemberRoleNo(Long memberRoleNo) {
        return jpaMemberRoleRepository.findByMemberRoleNo(memberRoleNo);
    }

    @Override
    public List<MemberRole> findAll() {
        return jpaMemberRoleRepository.findAll();
    }

    @Override
    public List<MemberRole> findAllByMemberUser(MemberUser memberUser) {
        return jpaMemberRoleRepository.findAllByMemberUser(memberUser);
    }

    @Override
    public List<MemberRole> findAllByRole(Role role) {
        return jpaMemberRoleRepository.findAllByRole(role);
    }

    @Override
    public MemberRole save(MemberRole memberRole) {
        return jpaMemberRoleRepository.save(memberRole);
    }

    @Override
    public List<MemberRole> saveAll(List<MemberRole> memberRoles) {
        return jpaMemberRoleRepository.saveAll(memberRoles);
    }

    @Override
    public void delete(MemberRole memberRole) {
        jpaMemberRoleRepository.delete(memberRole);
    }

    @Override
    public void deleteAll(List<MemberRole> memberRoles) {
        jpaMemberRoleRepository.deleteAll(memberRoles);
    }
}
