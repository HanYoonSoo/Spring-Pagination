package com.example.springpagingpractice.repository;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PostsSearch {
    private String title;
    private String content;
    private String author;

    @NotEmpty
    private String orderBy;

    @NotEmpty
    private String sort;

    public PostsSearch(){
        this.orderBy = "DESC";
        this.sort = "id";
    }
}
