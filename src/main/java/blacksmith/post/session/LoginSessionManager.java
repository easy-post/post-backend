package blacksmith.post.session;

//@Component
//public class LoginSessionManager {
//    public static final String SESSION_COOKIE_NAME = "loginSessionId";
//    private static final int SESSION_EXPIRATION_TIME = 1800;
//    private Map<String, MemberInfoDto> sessionStore = new ConcurrentHashMap<>();
//    //    30분 지난 세션 자동으로 지워야 하는데.
////    삭제는, js에서 beforeunload 이벤트 때 logout 요청을 날리자. react의 App 컴포넌트에서 하면 될 듯 아니면 종료 생명주기
////    재 로그인 시 기존 sessionId 삭제. 먼저 sessionId 받은 다음에 null이 아니면 찾아서 null이 아니면 삭제.
//    public LoginSessionDto createSession(MemberInfoDto memberInfoDto,  HttpServletResponse response){
//        String sessionId = UUID.randomUUID().toString();
//        sessionStore.put(sessionId, memberInfoDto);
//
//        ResponseCookieBuilder cookieBuilder = from(SESSION_COOKIE_NAME, sessionId);
//        cookieBuilder.maxAge(SESSION_EXPIRATION_TIME);
//        cookieBuilder.secure(true);
//        cookieBuilder.sameSite("None");
////        sessionCookie.setDomain("127.0.0.1");
//
//        response.addHeader("Set-Cookie", cookieBuilder.build().toString());
//        return new LoginSessionDto(sessionId, true);
//    }
//
//    public Optional<MemberInfoDto> findMemberInfoBySessionId(String sessionId){
//        return Optional.ofNullable(sessionStore.get(sessionId));
//    }
//
//    public void expire(String sessionId, HttpServletResponse response){
//        if(sessionId == null) return;
//        sessionStore.remove(sessionId);
//        expireCookie(response, sessionId);
//    }
//
//    private void expireCookie(HttpServletResponse response, String sessionId){
//        ResponseCookieBuilder cookieBuilder = from(SESSION_COOKIE_NAME, sessionId);
//        cookieBuilder.maxAge(0);
//        cookieBuilder.secure(true);
//        cookieBuilder.sameSite("None");
//        response.addHeader("Set-Cookie", cookieBuilder.build().toString());
//    }
//}
