package blacksmith.post.controller;

import blacksmith.post.domain.Member;
import blacksmith.post.domain.dtos.*;
import blacksmith.post.exceptions.member.MemberInvalidLoginException;
import blacksmith.post.exceptions.member.MemberLoginValidException;
import blacksmith.post.exceptions.member.MemberRegisterBlankException;
import blacksmith.post.exceptions.member.MemberRegisterValidException;
import blacksmith.post.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
//@CrossOrigin("*")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ValidDto register(@Validated @RequestBody MemberRegisterDto registerDto, BindingResult bindingResult){
        log.info(registerDto.toString());
        if(bindingResult.hasErrors()){
            throw new MemberRegisterValidException("입력값을 다시 확인해 주세요.");
        }

        memberService.register(registerDto);
        return new ValidDto(true);
    }

    @GetMapping("/")
    public List<MemberInfoDto> showMembers(){
        return memberService.findAll();
    }

    @GetMapping("/valid/nickname/{nickname}")
    public boolean validNickname(@PathVariable("nickname") String nickname){
        System.out.println("nickname = " + nickname);
        return memberService.validDuplicateNickname(nickname);
    }

    @PostMapping("/login")
    public LoginSessionDto login(@Validated @RequestBody MemberLoginDto loginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MemberLoginValidException("입력값을 다시 확인 해 주세요.");
        }
        Optional<LoginSessionDto> sessionDto = memberService.login(loginDto);
        if(sessionDto.isPresent()){
            return sessionDto.get();
        }else{
            throw new MemberInvalidLoginException("아이디나 패스워드가 일치하지 않습니다.");
        }
    }

    @GetMapping("/valid/login")
    public void validLogin(@CookieValue(name = "sessionId", required = false) String sessionId ,HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        log.info("origin : {}",origin);
        log.info("cookie : {}",sessionId);
        log.info("time : {}", LocalTime.now());
    }
}
