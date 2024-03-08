package com.example.springpagingpractice.repository;

import com.example.springpagingpractice.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentsRepository extends JpaRepository<Comments, Long>, CommentsCustom{

    @EntityGraph(attributePaths = {"posts"})
    @Query("select c from Comments c where c.content = :content")
    Page<Comments> findCommentsWithPaging(String content, Pageable pageable);
}
