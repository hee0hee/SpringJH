package com.example.spring_jh.controller;

import com.example.spring_jh.dto.PostRequestDto;
import com.example.spring_jh.dto.PostResponseDto;
import com.example.spring_jh.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/post/{id}")
    public PostResponseDto detailPost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.detailPost(id, requestDto);
    }

}
