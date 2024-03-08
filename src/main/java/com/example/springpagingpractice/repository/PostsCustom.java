package com.example.springpagingpractice.repository;

import com.example.springpagingpractice.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsCustom {
    Page<Posts> findPostsWithPaging(Pageable pageable, PostsSearch postsSearch);
}
