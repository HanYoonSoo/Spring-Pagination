package com.example.springpagingpractice.repository;

import com.example.springpagingpractice.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long>, PostsCustom{
}
