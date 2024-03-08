package com.example.springpagingpractice.dto;


import com.example.springpagingpractice.entity.Posts;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public static PostsResponseDto create(Posts entity) {
        PostsResponseDto postsResponseDto = new PostsResponseDto();
        postsResponseDto.setId(entity.getId());
        postsResponseDto.setTitle(entity.getTitle());
        postsResponseDto.setContent(entity.getContent());
        postsResponseDto.setAuthor(entity.getAuthor());

        return postsResponseDto;
    }
}
