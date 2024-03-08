package com.example.springpagingpractice.repository;

import com.example.springpagingpractice.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentsCustom {
    public Page<Comments> findCommentsWithPagingWithQueryDSL(Pageable pageable);
}
