package com.example.springpagingpractice.service;

import com.example.springpagingpractice.dto.CommentsResponseDto;
import com.example.springpagingpractice.dto.PageResponseDto;
import com.example.springpagingpractice.entity.Comments;
import com.example.springpagingpractice.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public PageResponseDto findCommentsWithPaging(String content, int pageNo){
        int page = pageNo == 0 ? 0 : pageNo - 1;
        int pageLimit = 20;

        Pageable pageable = PageRequest.of(page, pageLimit, Sort.by(DESC, "id"));

        Page<Comments> commentsList = commentsRepository.findCommentsWithPaging(content, pageable);

        return PageResponseDto.create(commentsList, CommentsResponseDto::create);
    }

    public PageResponseDto findCommentsWithPagingWithQueryDSL(int pageNo){
        int page = pageNo == 0 ? 0 : pageNo - 1;
        int pageLimit = 20;

        Pageable pageable = PageRequest.of(page, pageLimit, Sort.by(DESC, "id"));

        Page<Comments> commentsList = commentsRepository.findCommentsWithPagingWithQueryDSL(pageable);

        return PageResponseDto.create(commentsList, CommentsResponseDto::create);
    }
}
