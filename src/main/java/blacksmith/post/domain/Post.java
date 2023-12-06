package blacksmith.post.domain;

import blacksmith.post.domain.base.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Post  extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String html;
}
