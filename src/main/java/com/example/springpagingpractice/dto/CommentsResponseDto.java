package com.example.springpagingpractice.dto;

import com.example.springpagingpractice.entity.Comments;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsResponseDto {
    private Long id;
    private String content;
    private PostsResponseDto postsResponseDto;

    public static CommentsResponseDto create(Comments entity) {
        CommentsResponseDto commentsResponseDto = new CommentsResponseDto();
        commentsResponseDto.setId(entity.getId());
        commentsResponseDto.setContent(entity.getContent());
        commentsResponseDto.setPostsResponseDto(PostsResponseDto.create(entity.getPosts()));

        return commentsResponseDto;
    }
}

