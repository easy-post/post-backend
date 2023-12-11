package blacksmith.post.redis.service;

import blacksmith.post.domain.dtos.member.LoginSessionDto;
import blacksmith.post.domain.dtos.member.MemberInfoDto;
import blacksmith.post.redis.entity.LoginMember;
import blacksmith.post.redis.repository.LoginMemberRepository;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseCookie.from;

@Service
@Slf4j
public class LoginMemberService {
    private final LoginMemberRepository loginMemberRepository;

    public static final String SESSION_COOKIE_NAME = "sessionId";
    private static final int SESSION_EXPIRATION_TIME = 1800;
    private static final String SESSION_COOKIE_DOMAIN = ".post-react.onrender.com";

    public LoginMemberService(LoginMemberRepository loginMemberRepository) {
        this.loginMemberRepository = loginMemberRepository;
    }


    public LoginSessionDto createSession(MemberInfoDto memberInfoDto, HttpServletResponse response){
        String sessionId = UUID.randomUUID().toString();
        LoginMember loginMember = new LoginMember(sessionId, memberInfoDto, SESSION_EXPIRATION_TIME);
        loginMemberRepository.save(loginMember);
        updateCookieTime(sessionId, SESSION_EXPIRATION_TIME, response);

        log.info("쿠키생성");
        return new LoginSessionDto(sessionId, true);
    }

    public Optional<MemberInfoDto> getMemberInfoBySessionId(String sessionId, HttpServletResponse response){
        Optional<LoginMember> findLoginMember = loginMemberRepository.findById(sessionId);

        if(findLoginMember.isPresent()){
            LoginMember extractLoginMember = findLoginMember.get();
            LoginMember newLoginMember = new LoginMember(extractLoginMember.getSessionId(), extractLoginMember.getMemberInfoDto(), SESSION_EXPIRATION_TIME);
            expire(extractLoginMember.getSessionId());
            loginMemberRepository.save(newLoginMember);
            updateCookieTime(sessionId, SESSION_EXPIRATION_TIME, response);
            return Optional.of(findLoginMember.get().getMemberInfoDto());
        }
        return Optional.empty();
    }

    public void expireAll(String sessionId, HttpServletResponse response){
        if(sessionId == null) return;
        loginMemberRepository.deleteById(sessionId);
        expireCookie(response, sessionId);
    }

    private void expire(String sessionId){
        loginMemberRepository.deleteById(sessionId);
    }

    private void expireCookie(HttpServletResponse response, String sessionId){
        updateCookieTime(sessionId, 0, response);
    }

    private static void updateCookieTime(String sessionId, int maxAgeSeconds, HttpServletResponse response) {
        ResponseCookie.ResponseCookieBuilder cookieBuilder = from(SESSION_COOKIE_NAME, sessionId);
        cookieBuilder.maxAge(maxAgeSeconds);
        cookieBuilder.secure(true);
        cookieBuilder.sameSite("None");
        cookieBuilder.path("/");
        cookieBuilder.httpOnly(true);
        cookieBuilder.domain(SESSION_COOKIE_DOMAIN);

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "https://post-react.onrender.com");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Set-Cookie", cookieBuilder.build().toString());
        log.info("cookie: {}",cookieBuilder.build().toString());
    }
}


