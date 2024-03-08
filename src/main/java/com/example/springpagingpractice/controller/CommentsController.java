package com.example.springpagingpractice.controller;

import com.example.springpagingpractice.dto.PageResponseDto;
import com.example.springpagingpractice.dto.SingleResponseDto;
import com.example.springpagingpractice.repository.PostsSearch;
import com.example.springpagingpractice.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsController {

    public final CommentsService commentsService;

    @GetMapping
    public ResponseEntity findCommentsWithPaging(@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                              @RequestParam("content") String content){
        PageResponseDto response = commentsService.findCommentsWithPaging(content, pageNo);
        return new ResponseEntity<>(new SingleResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/queryDSL")
    public ResponseEntity findCommentsWithPagingWithQueryDSL(@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo){
        PageResponseDto response = commentsService.findCommentsWithPagingWithQueryDSL(pageNo);
        return new ResponseEntity<>(new SingleResponseDto(response), HttpStatus.OK);
    }

}
