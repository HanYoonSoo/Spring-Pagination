//package com.example.springpagingpractice.config;
//
//import com.example.springpagingpractice.entity.Comments;
//import com.example.springpagingpractice.entity.Posts;
//import jakarta.annotation.PostConstruct;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init(){
//        initService.dbInit1();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService{
//
//        private final EntityManager em;
//        public void dbInit1(){
//            for(int i = 0; i < 100; i++){
//                Posts posts = Posts.builder()
//                        .title("글" + i)
//                        .content("" + (100 - i))
//                        .author("" + i)
//                        .build();
//                em.persist(posts);
//
//                for(int j = 0; j < 1000; j++){
//                    em.persist(Comments.builder()
//                            .posts(posts)
//                            .content("Comments" + j)
//                            .build());
//                }
//            }
//
//        }
//    }
//}