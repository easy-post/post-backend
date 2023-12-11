package blacksmith.post.redis.entity;

import blacksmith.post.domain.dtos.member.MemberInfoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class LoginMember implements Serializable {
    @Id
    private String sessionId;

    private MemberInfoDto memberInfoDto;

    @TimeToLive
    private long ttl;

    public LoginMember(String sessionId,MemberInfoDto memberInfoDto, long ttl) {
        this.sessionId = sessionId;
        this.memberInfoDto = memberInfoDto;
        this.ttl = ttl;
    }
}
