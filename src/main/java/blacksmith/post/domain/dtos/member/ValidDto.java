package blacksmith.post.domain.dtos.member;

import lombok.Data;

@Data
public class ValidDto {
    private Boolean success;

    public ValidDto(Boolean success) {
        this.success = success;
    }
}
