package com.example.springpagingpractice.repository;

import com.example.springpagingpractice.entity.Posts;
import com.example.springpagingpractice.entity.QPosts;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.springpagingpractice.entity.QPosts.posts;

@Repository
@RequiredArgsConstructor
public class PostsCustomImpl implements PostsCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Posts> findPostsWithPaging(Pageable pageable, PostsSearch postsSearch) {
        // Load Data
        List<Posts> postsList = jpaQueryFactory
                .selectFrom(QPosts.posts)
                .where(searchEq(postsSearch))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(postsSort(pageable))
                .fetch();

        // Count data
        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(posts.count())
                .from(posts)
                .where(searchEq(postsSearch));

        return PageableExecutionUtils.getPage(postsList, pageable, countQuery::fetchOne);
    }

    private OrderSpecifier<?> postsSort(Pageable pageable) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : pageable.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
                switch (order.getProperty()){
                    case "title":
                        return new OrderSpecifier(direction, posts.title);
                    case "content":
                        return new OrderSpecifier(direction, posts.content);
                    case "author":
                        return new OrderSpecifier(direction, posts.author);
                    default:
                        return new OrderSpecifier(direction, posts.id);
                }
            }
        }
        return null;
    }

    private BooleanBuilder searchEq(PostsSearch postsSearch) {
        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.hasText(postsSearch.getTitle())){
            builder.and(posts.title.like("%" + postsSearch.getTitle() + "%"));
        }

        if(StringUtils.hasText(postsSearch.getContent())){
            builder.and(posts.content.like("%"+postsSearch.getContent() +"%"));
        }

        if(StringUtils.hasText(postsSearch.getAuthor())){
            builder.and(posts.author.like("%" + postsSearch.getAuthor() + "%"));
        }

        return builder;
    }
}
