package blacksmith.post.service;

import blacksmith.post.domain.Member;
import blacksmith.post.domain.dtos.member.LoginSessionDto;
import blacksmith.post.domain.dtos.member.MemberInfoDto;
import blacksmith.post.domain.dtos.member.MemberLoginDto;
import blacksmith.post.domain.dtos.member.MemberRegisterDto;
import blacksmith.post.exceptions.hash.HashNoSuchAlgorithmException;
import blacksmith.post.exceptions.member.MemberLoginIdDuplicateException;
import blacksmith.post.exceptions.member.MemberNicknameDuplicateException;
import blacksmith.post.redis.service.LoginMemberService;
import blacksmith.post.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final LoginMemberService loginMemberService;
    private final Hash hash = new Hash();

    public void register(MemberRegisterDto registerDto) {
        Optional<Member> memberForcheck = memberRepository.findByLoginId(registerDto.getLoginId());
        if(memberForcheck.isPresent()){
            log.info("member loginid : {}", memberForcheck.get().getLoginId());
            throw new MemberLoginIdDuplicateException("해당 아이디는 이미 존재합니다.");
        }

        registerDto.setPassword(hash.encrypt(registerDto.getPassword()));
        memberRepository.save(registerDto.toMember());
    }

    public List<MemberInfoDto> findAll(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberInfoDto::new).toList();
    }

    public List<Member> findAllEntity(){
        return memberRepository.findAll();
    }

    public boolean validDuplicateNickname(String nickname){
        Optional<Member> memberForcheck = memberRepository.findByNickname(nickname);
        if(memberForcheck.isPresent()){
            throw new MemberNicknameDuplicateException("중복된 닉네임 입니다.");
        }
        return true;
    }

    public Optional<LoginSessionDto> login(MemberLoginDto loginDto,  HttpServletResponse response){
        Optional<Member> findMember = memberRepository.findByLoginId(loginDto.getLoginId());
        if(findMember.isPresent()){
            String hashPwd = hash.encrypt(loginDto.getPassword());
            Member extractMember = findMember.get();
            if(extractMember.getPassword().equals(hashPwd)){
                MemberInfoDto memberInfo = new MemberInfoDto(extractMember.getId(), loginDto.getLoginId(), extractMember.getNickname());
                return Optional.of(loginMemberService.createSession(memberInfo, response));
            }
        }
        return Optional.empty();
    }

    public void expireLoginSession(String sessionId, HttpServletResponse response){
        loginMemberService.expireAll(sessionId, response);
    }

    public Optional<MemberInfoDto> getLoginMember(String sessionId, HttpServletResponse response){
        return loginMemberService.getMemberInfoBySessionId(sessionId, response);
    }


    private class Hash{
        private static final String HASH_ALGORITHM = "SHA-256";

        public String encrypt(String text) {
            try{
                MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
                md.update(text.getBytes());

                return bytesToHex(md.digest());
            } catch (NoSuchAlgorithmException e){
                throw new HashNoSuchAlgorithmException(e);
            }
        }

        private String bytesToHex(byte[] bytes){
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        }
    }
}
