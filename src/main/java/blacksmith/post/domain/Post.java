package blacksmith.post.domain;

import blacksmith.post.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post  extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;

    @Column(columnDefinition = "text")
    private String html;

    public Post(Member member, String title, String html){
        this.member = member;
        this.title = title;
        this.html = html;
    }
}
