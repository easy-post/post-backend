package blacksmith.post.redis.service;

import static org.springframework.http.ResponseCookie.from;

//@Service
//@RequiredArgsConstructor
//public class LoginMemberService {
//    private final RedisTemplate<String, LoginMember> redisTemplate;
//    public static final String SESSION_COOKIE_NAME = "loginSessionId";
//    private static final int SESSION_EXPIRATION_TIME = 1800;
//
//
//    public LoginSessionDto createSession(MemberInfoDto memberInfoDto, HttpServletResponse response){
//        String sessionId = UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(sessionId, new LoginMember(sessionId, memberInfoDto), SESSION_EXPIRATION_TIME);
//
//        updateTime(sessionId, SESSION_EXPIRATION_TIME, response);
//        return new LoginSessionDto(sessionId, true);
//    }
//
//    public Optional<MemberInfoDto> getMemberInfoBySessionId(String sessionId, HttpServletResponse response){
//        Optional<LoginMember> loginMember = Optional.of((LoginMember) redisTemplate.opsForValue().get(sessionId));
//        if(loginMember.isPresent()){
//            LoginMember extractLoginMember = loginMember.get();
////            extractLoginMember.timeUpdate(SESSION_EXPIRATION_TIME);
//            updateTime(sessionId, SESSION_EXPIRATION_TIME, response);
//
//            return Optional.of(loginMember.get().getMemberInfoDto());
//        }
//        return Optional.empty();
//    }
//
//    public void expire(String sessionId, HttpServletResponse response){
//        if(sessionId == null) return;
//        redisTemplate.delete(sessionId);
//        expireCookie(response, sessionId);
//    }
//
//    private void expireCookie(HttpServletResponse response, String sessionId){
//        updateTime(sessionId, 0, response);
//    }
//
//    private static void updateTime(String sessionId, int maxAgeSeconds, HttpServletResponse response) {
//        ResponseCookie.ResponseCookieBuilder cookieBuilder = from(SESSION_COOKIE_NAME, sessionId);
//        cookieBuilder.maxAge(maxAgeSeconds);
//        cookieBuilder.secure(true);
//        cookieBuilder.sameSite("None");
//        response.addHeader("Set-Cookie", cookieBuilder.build().toString());
//    }
//
//}
