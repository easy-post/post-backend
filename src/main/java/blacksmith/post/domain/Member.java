package blacksmith.post.domain;


import blacksmith.post.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(columnDefinition = "varchar(16)")
    private String loginId;
    private String password;

    @Column(columnDefinition = "varchar(16)")
    private String nickname;

    public Member(String loginId, String password, String nickname){
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public Member(Long id, String loginId, String nickname){
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
    }
}
