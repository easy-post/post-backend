package blacksmith.post.repository.custom;

import blacksmith.post.domain.Post;
import blacksmith.post.domain.QMember;
import blacksmith.post.domain.QPost;
import blacksmith.post.domain.dtos.post.PostListElementDto;
import blacksmith.post.domain.dtos.post.PostSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.function.Supplier;

public class PostRepositoryImpl implements PostCustomRepository{
    private final EntityManager em;
    private final JPAQueryFactory query;
    private final QPost p = QPost.post;
    private final QMember m  = QMember.member;

    public PostRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


//    limit 등 해서 페이징 작업 추가해야 함
    public Page<PostListElementDto> getPostListByPage(PostSearchCondition condition, Pageable pageable){
        List<PostListElementDto> postEls = query.select(Projections.constructor(PostListElementDto.class, p.id, p.title, m.nickname, p.createdDate))
                .from(p).join(p.member, m)
                .where(nicknameEq(condition.getNickname()), titleLike(condition.getTitle()))
                .orderBy(p.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = query.select(p.count()).from(p).join(p.member, m)
                .where(nicknameEq(condition.getNickname()), titleLike(condition.getTitle()));

        return PageableExecutionUtils.getPage(postEls, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostListElementDto> getPostListByMemberId(Long memberId, Pageable pageable) {
        List<PostListElementDto> postEls = query.select(Projections.constructor(PostListElementDto.class, p.id, p.title, m.nickname, p.createdDate))
                .from(p).join(p.member, m)
                .where(m.id.eq(memberId))
                .orderBy(p.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        TypedQuery<Long> countQuery = em.createQuery("select count(p) from Post p where p.member.id = :memberId", Long.class)
                .setParameter("memberId", memberId);

        return PageableExecutionUtils.getPage(postEls, pageable,countQuery::getSingleResult);
    }


    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f){
        try{
            return new BooleanBuilder(f.get());
        }catch(IllegalArgumentException e){
            return new BooleanBuilder();
        }
    }

    private BooleanBuilder nicknameEq(String nickname){
        return nullSafeBuilder(()->m.nickname.eq(nickname));
    }

    private BooleanBuilder titleLike(String title){
        if(title == null) return new BooleanBuilder();
        return nullSafeBuilder(()->p.title.contains(title));
    }
}
