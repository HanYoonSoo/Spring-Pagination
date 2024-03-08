package com.example.springpagingpractice.repository;

import com.example.springpagingpractice.entity.Comments;
import com.example.springpagingpractice.entity.Posts;
import com.example.springpagingpractice.entity.QPosts;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.springpagingpractice.entity.QComments.comments;
import static com.example.springpagingpractice.entity.QPosts.posts;

@Repository
@RequiredArgsConstructor
public class CommentsCustomImpl implements CommentsCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Comments> findCommentsWithPagingWithQueryDSL(Pageable pageable) {
        // Load Data
        List<Comments> commentsList = jpaQueryFactory
                .selectFrom(comments)
                .leftJoin(comments.posts, posts).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(commentsSort(pageable))
                .fetch();


        // Count data
        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(comments.count())
                .from(comments);

        return PageableExecutionUtils.getPage(commentsList, pageable, countQuery::fetchOne);
    }

    private OrderSpecifier<?> commentsSort(Pageable pageable) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : pageable.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                return new OrderSpecifier<>(direction, comments.id);
            }
        }
        return null;
    }
}
