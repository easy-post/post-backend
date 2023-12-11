package blacksmith.post.domain.dtos.post;

import blacksmith.post.domain.Post;
import blacksmith.post.domain.dtos.member.MemberInfoDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PostDto {
    private Long id;
    private MemberInfoDto memberInfoDto;
    private String title;
    private String html;

    public PostDto(String title, String content) {
        this.title = title;
        this.html = content;
    }

    public PostDto(Post post){
        this.id = post.getId();
        this.memberInfoDto = new MemberInfoDto(post.getMember());
        this.title = post.getTitle();
        this.html = post.getHtml();
    }
}
