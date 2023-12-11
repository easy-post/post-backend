package blacksmith.post;

import blacksmith.post.exceptions.handler.UserHandlerExceptionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApiConfig implements WebMvcConfigurer {
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new UserHandlerExceptionResolver());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        로그인 주소와 react 주소 (나중에는 둘이 통합하여 frontend 서버 주소)
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:63472", "http://localhost:3000", "http://127.0.0.1:3333", "http://192.168.229.45:3000",
                        "http://localhost:3001", "https://post-react.onrender.com")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
