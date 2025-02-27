package nvp_api.member.infrastructure.repository;

import nvp_api.member.domain.aggregate.BlackList;
import nvp_api.member.domain.repository.BlackListRepository;
import org.springframework.data.repository.CrudRepository;

public interface CrudBlackListRepository extends BlackListRepository, CrudRepository<BlackList, String> {

}
