package blacksmith.post.domain.dtos.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostListElementDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdTime;

    public PostListElementDto(Long id, String title, String nickname, LocalDateTime createdTime) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdTime = createdTime;
    }
}
