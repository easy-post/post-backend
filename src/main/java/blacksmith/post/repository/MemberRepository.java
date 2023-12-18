package blacksmith.post.repository;

import blacksmith.post.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByLoginId(String loginId);
    public Optional<Member> findByNickname(String nickname);
}
