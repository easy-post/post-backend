package blacksmith.post.domain.dtos.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSaveResultDto {
    Long id;
    boolean success;
}
