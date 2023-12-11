package blacksmith.post.redis.repository;

import blacksmith.post.redis.entity.LoginMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface LoginMemberRepository extends CrudRepository<LoginMember, String> {
    Optional<LoginMember> findBySessionId(String sessionId);
}
