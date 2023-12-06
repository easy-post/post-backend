package blacksmith.post.domain.dtos;

import blacksmith.post.domain.Member;
import lombok.Data;

@Data
public class MemberInfoDto {
    private Long id;
    private String loginId;
    private String nickname;

    public MemberInfoDto(Member member){
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.nickname = member.getNickname();
    }

    public MemberInfoDto(Long id, String loginId, String nickname){
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
    }
}
