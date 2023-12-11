package blacksmith.post.service;

import blacksmith.post.domain.Member;
import blacksmith.post.domain.Post;
import blacksmith.post.domain.dtos.post.PostDto;
import blacksmith.post.domain.dtos.post.PostListElementDto;
import blacksmith.post.domain.dtos.post.PostSearchCondition;
import blacksmith.post.exceptions.member.MemberNotExistException;
import blacksmith.post.repository.MemberRepository;
import blacksmith.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long save(PostDto postDto){
        Optional<Member> findMember = memberRepository.findById(postDto.getMemberInfoDto().getId());
        if(findMember.isEmpty()){
            throw new MemberNotExistException("회원이 존재하지 않습니다.");
        }

        Post post = new Post(findMember.get(), postDto.getTitle(), postDto.getHtml());
        postRepository.save(post);

        return post.getId();
    }

    public Optional<PostDto> getPostOne(String postId){
        Optional<Post> post =postRepository.findFetch(postId);
        if(post.isPresent()){
            return Optional.of(new PostDto(post.get()));
        }
        return Optional.empty();
    }

    public Page<PostListElementDto> getPostElements(PostSearchCondition condition, Pageable pageable){
        return postRepository.getPostListByPage(condition, pageable);
    }
}
