package blacksmith.post.domain.dtos.member;

import lombok.Data;

@Data
public class LoginSessionDto {
    private String sessionId;
    private boolean success;
    private String cookieStr;

    public LoginSessionDto(String sessionId, boolean success, String cookieStr) {
        this.sessionId = sessionId;
        this.success = success;
        this.cookieStr = cookieStr;
    }

    public LoginSessionDto() {
    }
}
