package blacksmith.post.repository.custom;


import blacksmith.post.domain.dtos.post.PostListElementDto;
import blacksmith.post.domain.dtos.post.PostSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    public Page<PostListElementDto> getPostListByPage(PostSearchCondition condition, Pageable pageable);
}
