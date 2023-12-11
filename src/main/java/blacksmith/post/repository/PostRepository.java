package blacksmith.post.repository;


import blacksmith.post.domain.Post;
import blacksmith.post.repository.custom.PostCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    @Query("select p from Post p join fetch p.member m where p.id = :postId")
    public Optional<Post> findFetch(@Param("postId") String postId);
}
