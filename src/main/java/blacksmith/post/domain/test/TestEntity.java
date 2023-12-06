package blacksmith.post.domain.test;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TestEntity {
    @Id @GeneratedValue
    @Column(name = "test_id")
    private Long id;

    private String username;
}
