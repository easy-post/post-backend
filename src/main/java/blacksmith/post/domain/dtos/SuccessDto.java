package blacksmith.post.domain.dtos;

import lombok.Data;

@Data
public class SuccessDto {
    private boolean success;

    public SuccessDto(boolean success) {
        this.success = success;
    }
}
