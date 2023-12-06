package blacksmith.post.domain.dtos;

import lombok.Data;

@Data
public class LoginSessionDto {
    private String sessionId;
    private boolean success;

    public LoginSessionDto(String sessionId, boolean success) {
        this.sessionId = sessionId;
        this.success = success;
    }

    public LoginSessionDto() {
    }
}
