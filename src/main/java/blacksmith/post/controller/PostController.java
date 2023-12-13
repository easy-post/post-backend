package blacksmith.post.controller;


import blacksmith.post.domain.dtos.SuccessDto;
import blacksmith.post.domain.dtos.member.MemberInfoDto;
import blacksmith.post.domain.dtos.post.*;
import blacksmith.post.exceptions.member.MemberNotLoginException;
import blacksmith.post.exceptions.post.PostIsNotMineException;
import blacksmith.post.exceptions.post.PostNotExistException;
import blacksmith.post.service.MemberService;
import blacksmith.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static blacksmith.post.redis.service.LoginMemberService.SESSION_COOKIE_NAME;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final MemberService memberService;
    private final PostService postService;

//    글을 쓰는 도중에 로그아웃되서 로그인 해야하는.. 그거를 좀 리액트쪽에서 React.memo 이런 걸로 되나? 글을 기억시키는?
    @PostMapping("/save")
    public PostSaveResultDto save(@CookieValue(name = SESSION_COOKIE_NAME) String sessionId, @RequestBody PostDto postDto, HttpServletResponse response){
        if(sessionId == null){
            throw new MemberNotLoginException("다시 로그인 해 주세요.");
        }
        Optional<MemberInfoDto> loginMember = memberService.getLoginMember(sessionId, response);
        if(loginMember.isEmpty()){
            throw new MemberNotLoginException("다시 로그인 해 주세요.");
        }
        postDto.setMemberInfoDto(loginMember.get());
        postDto.setTitle(postDto.getTitle().replace("<script>", "&lt;script&gt;")
                .replace("</script>", "&lt;/script&gt;"));
        Long postId = postService.save(postDto);

        return new PostSaveResultDto(postId, true);
    }

    @GetMapping("/{postId}")
    public PostDto getPost(@PathVariable("postId") Long postId){
        Optional<PostDto> post = postService.getPostOne(postId);
        if(post.isEmpty()){
            throw new PostNotExistException("해당 게시글은 존재하지 않습니다.");
        }

        return post.get();
    }

    @GetMapping
    public Page<PostListElementDto> getPostEls(PostSearchCondition condition, Pageable pageable, HttpServletRequest request){
        log.info("uri : {}", request.getRequestURI());
        String title = condition.getTitle();
        String nickname = condition.getNickname();

        if(title != null){
            if(title.isBlank()) condition.setTitle(null);
        }
        if(nickname != null){
            if(nickname.isBlank()) condition.setNickname(null);
        }
        return postService.getPostElements(condition, pageable);
    }

    @GetMapping("/member")
    public Page<PostListElementDto> getPostElsByMemberId(@CookieValue(name = SESSION_COOKIE_NAME) String sessionId, Pageable pageable, HttpServletResponse response){
        Optional<MemberInfoDto> loginMember = memberService.getLoginMember(sessionId, response);
        if(loginMember.isEmpty()){
            throw new MemberNotLoginException("다시 로그인 해 주세요.");
        }
        return postService.getPostElementsByMember(loginMember.get(), pageable);
    }

    @GetMapping("{postId}/edit")
    public PostDto checkIsMyPost(@CookieValue(name = SESSION_COOKIE_NAME) String sessionId, @PathVariable("postId") Long postId, HttpServletResponse response){
        Optional<MemberInfoDto> loginMember = memberService.getLoginMember(sessionId, response);
        Optional<PostDto> postOne = postService.getPostOne(postId);

        validMemberAndPost(loginMember, postOne);

        return postOne.get();
    }


    @PostMapping("{postId}/edit")
    public PostSaveResultDto updatePost(@CookieValue(name = SESSION_COOKIE_NAME)String sessionId, @PathVariable("postId") Long postId, PostDto postDto,HttpServletResponse response){
        Optional<MemberInfoDto> loginMember = memberService.getLoginMember(sessionId, response);

        if(loginMember.isEmpty()){
            throw new MemberNotLoginException("다시 로그인 해 주세요.");
        }
        postService.update(loginMember.get().getId(), postId, postDto);
        return new PostSaveResultDto(postId, true);
    }

    private void validMemberAndPost(Optional<MemberInfoDto> loginMember, Optional<PostDto> postOne){
        if(loginMember.isEmpty()){
            throw new MemberNotLoginException("다시 로그인 해 주세요.");
        }

        if(postOne.isEmpty()){
            throw new PostNotExistException("해당 게시글은 존재하지 않습니다.");
        }

        if(!loginMember.get().getId().equals(postOne.get().getMemberInfoDto().getId())){
            throw new PostIsNotMineException("로그인 한 사용자의 게시글이 아닙니다.");
        }
    }



}
