package blacksmith.post.domain;

import blacksmith.post.domain.base.BaseEntity;
import jakarta.persistence.*;

@Entity
public class PostImg extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "post_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    Post post;

    String path;

    @Column(columnDefinition = "char(36)")
    String fileName;
}
