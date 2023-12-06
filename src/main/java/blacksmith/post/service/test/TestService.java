package blacksmith.post.service.test;

import blacksmith.post.domain.test.TestEntity;
import blacksmith.post.repository.test.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    public Long save(TestEntity testEntity){
        testRepository.save(testEntity);
        return testEntity.getId();
    }

    public Optional<TestEntity> findById(Long id){
        return testRepository.findById(id);
    }

    public List<TestEntity> findAll(){
        return testRepository.findAll();
    }
}
