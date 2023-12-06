package blacksmith.post.domain.dtos;

import blacksmith.post.domain.Member;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberRegisterDto {
    @NotBlank(message = "ID가 비어있습니다.")
    @Size(min = 1, max = 16, message = "ID는 1~16자 여야 합니다.")
    private String loginId;

    @NotBlank(message = "닉네임이 비어있습니다.")
    @Size(min = 1, max = 16, message = "닉네임은 1~16자 여야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;

    public Member toMember(){
        return new Member(loginId, password, nickname);
    }
}
