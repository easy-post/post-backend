package blacksmith.post.controller;

import blacksmith.post.domain.dtos.member.*;
import blacksmith.post.exceptions.member.*;
import blacksmith.post.redis.service.LoginMemberService;
import blacksmith.post.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static blacksmith.post.redis.service.LoginMemberService.SESSION_COOKIE_NAME;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final LoginMemberService loginMemberService;


    @PostMapping("/register")
    public ValidDto register(@Validated @RequestBody MemberRegisterDto registerDto, BindingResult bindingResult){
        log.info(registerDto.toString());
        if(bindingResult.hasErrors()){
            throw new MemberRegisterValidException("입력값을 다시 확인해 주세요.");
        }

        memberService.register(registerDto);
        return new ValidDto(true);
    }

    @GetMapping
    public List<MemberInfoDto> showMembers(){
        return memberService.findAll();
    }

    @GetMapping("/valid/nickname/{nickname}")
    public boolean validNickname(@PathVariable("nickname") String nickname){
        System.out.println("nickname = " + nickname);
        return memberService.validDuplicateNickname(nickname);
    }

    @PostMapping("/login")
    public LoginSessionDto login(@Validated @RequestBody MemberLoginDto loginDto, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request){
        log.info("host name url: {}", request.getRequestURL());
        log.info("host name uri: {}", request.getRequestURI());
        memberService.login(loginDto, response);
        if(bindingResult.hasErrors()){
            throw new MemberLoginValidException("입력값을 다시 확인 해 주세요.");
        }

        Optional<LoginSessionDto> sessionDto = memberService.login(loginDto, response);

        if(sessionDto.isEmpty()){
            throw new MemberInvalidLoginException("아이디나 패스워드가 일치하지 않습니다.");
        }

        return sessionDto.get();
    }

    @GetMapping("/valid-login")
    public void validLogin(@CookieValue(name = SESSION_COOKIE_NAME, required = false) String sessionId , HttpServletResponse response){
        log.info("sessionId : {}", sessionId);
        if(sessionId == null){
            throw new MemberNotLoginException("로그인 한 상태가 아닙니다.");
        }
        Optional<MemberInfoDto> loginMemberInfo = memberService.getLoginMember(sessionId, response);
        if(loginMemberInfo.isPresent()){
            return;
        }else {
            throw new MemberNotLoginException("다시 로그인 해 주세요.");
        }
    }


    @GetMapping("/logout")
    public void logout(@CookieValue(name = SESSION_COOKIE_NAME, required = false) String sessionId, HttpServletRequest request, HttpServletResponse response){
        memberService.expireLoginSession(sessionId, response);
    }
}
