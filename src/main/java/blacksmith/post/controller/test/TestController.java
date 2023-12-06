package blacksmith.post.controller.test;

import blacksmith.post.domain.test.TestEntity;
import blacksmith.post.service.test.TestService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
//    @GetMapping("/")
//    public String test(){
//        return "success";
//    }

    @PostMapping("/test/save")
    public void save(@RequestParam("username") String username, HttpServletResponse response) throws IOException {
        TestEntity testEntity = new TestEntity();
        testEntity.setUsername(username);

        Long savedTestId = testService.save(testEntity);
        response.sendRedirect("/test/"+savedTestId);
    }

    @GetMapping("/test")
    public List<TestEntity> all(){
        return testService.findAll();
    }

    @GetMapping("/test/{id}")
    public TestEntity find(@PathVariable("id") Long id){
//        System.out.println("id = " + id);
        return testService.findById(id).orElseThrow();
    }
}

