package com.example.springpagingpractice.controller;

import com.example.springpagingpractice.dto.PageResponseDto;
import com.example.springpagingpractice.dto.SingleResponseDto;
import com.example.springpagingpractice.repository.PostsSearch;
import com.example.springpagingpractice.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostsController {

    public final PostsService postsService;

    @GetMapping
    public ResponseEntity findPostsWithPaging(@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                                 @ModelAttribute("postSearch") PostsSearch postsSearch){
        PageResponseDto response = postsService.findPostsWithPaging(pageNo, postsSearch);
        return new ResponseEntity<>(new SingleResponseDto(response), HttpStatus.OK);
    }
}
