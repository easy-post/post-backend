package blacksmith.post.repository.custom;


import blacksmith.post.domain.Post;
import blacksmith.post.domain.dtos.post.PostListElementDto;
import blacksmith.post.domain.dtos.post.PostSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface PostCustomRepository {
    public Page<PostListElementDto> getPostListByPage(PostSearchCondition condition, Pageable pageable);
    public Page<PostListElementDto> getPostListByMemberId(Long memberId, Pageable pageable);
}
