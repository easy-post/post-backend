package blacksmith.post.domain.dtos.post;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class PostListBySessionDto {
    private String sessionId;
    private Pageable pageable;
}
