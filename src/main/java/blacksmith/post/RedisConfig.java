package blacksmith.post;


import blacksmith.post.redis.entity.LoginMember;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, LoginMember> redisTemplate(){
//        https://developer-nyong.tistory.com/21#article-2-1--refreshtoken
        RedisTemplate<String, LoginMember> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        Jackson2JsonRedisSerializer<LoginMember> serializer = new Jackson2JsonRedisSerializer<>(LoginMember.class);

        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);

        return redisTemplate;
    }
}
