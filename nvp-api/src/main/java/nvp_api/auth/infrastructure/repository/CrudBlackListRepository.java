package nvp_api.auth.infrastructure.repository;

import nvp_api.auth.domain.aggregate.BlackList;
import nvp_api.auth.domain.repository.BlackListRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

public interface CrudBlackListRepository extends BlackListRepository, CrudRepository<BlackList, String> {

}
