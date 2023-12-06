package blacksmith.post.session;

import blacksmith.post.domain.dtos.LoginSessionDto;
import blacksmith.post.domain.dtos.MemberInfoDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginSessionManager {
    public static final String SESSION_COOKIE_NAME = "loginSessionId";
    private Map<String, MemberInfoDto> sessionStore = new ConcurrentHashMap<>();

    public LoginSessionDto createSession(MemberInfoDto memberInfoDto){
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, memberInfoDto);

        return new LoginSessionDto(sessionId, true);
    }

    public Optional<MemberInfoDto> findMemberInfoBySessionId(String sessionId){
        return Optional.ofNullable(sessionStore.get(sessionId));
    }

    public void expire(String sessionId){
        sessionStore.remove(sessionId);
    }
}
