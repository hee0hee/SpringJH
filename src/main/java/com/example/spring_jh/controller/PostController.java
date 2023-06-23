package com.example.spring_jh.controller;

import com.example.spring_jh.dto.PostRequestDto;
import com.example.spring_jh.dto.PostResponseDto;
import com.example.spring_jh.entity.Post;
import com.example.spring_jh.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api") // 중복되는 path 한 번에 처리하기
public class PostController {
    private final PostService postService;

    public PostController(PostService memoService) {
        this.postService = memoService;
    }

    @GetMapping("/posts")
    // 조회하는 API
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @PutMapping("/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.deletePost(id, requestDto);
    }

//    @GetMapping("/post/{id}")
//    public Long detailPosts(@PathVariable Long id) {
//        //상세 조회
//        if(postList.containsKey(id)) {
//            // 해당 post 가져오기(get)
//            postList.get(id).getTitle();
//            postList.get(id).getContents();
//            postList.get(id).getAuthor();
//            postList.get(id).getPassword();
//            return id;
//        } else {
//            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
//        }
//    }

}
