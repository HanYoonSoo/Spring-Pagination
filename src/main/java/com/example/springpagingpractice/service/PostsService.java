package com.example.springpagingpractice.service;

import com.example.springpagingpractice.dto.PageResponseDto;
import com.example.springpagingpractice.dto.PostsResponseDto;
import com.example.springpagingpractice.entity.Posts;
import com.example.springpagingpractice.repository.PostsRepository;
import com.example.springpagingpractice.repository.PostsSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;


    public PageResponseDto findPostsWithPaging(int pageNo, PostsSearch postsSearch) {
        int page = pageNo == 0 ? 0 : pageNo - 1;
        int pageLimit = 10;

//        if(postsSearch == null){
//            postsSearch = new PostsSearch();
//            postsSearch.setOrderBy("DESC");
//            postsSearch.setSort("id");
//        }

        System.out.println(postsSearch.getOrderBy());

        Pageable pageable = PageRequest.of(page, pageLimit,
                Sort.by(Sort.Direction.fromString(postsSearch.getOrderBy()),
                        postsSearch.getSort()));

        Page<Posts> posts = postsRepository.findPostsWithPaging(pageable, postsSearch);

        return PageResponseDto.create(posts, PostsResponseDto::create);
    }
}
