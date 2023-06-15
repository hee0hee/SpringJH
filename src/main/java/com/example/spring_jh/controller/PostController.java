package com.example.spring_jh.controller;

import com.example.spring_jh.dto.PostRequestDto;
import com.example.spring_jh.dto.PostResponseDto;
import com.example.spring_jh.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api") // 중복되는 path 한 번에 처리하기
public class PostController {

    @GetMapping("/posts")
    // 조회하는 API
    public List<PostResponseDto> getPosts() {
        // Map To List
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList(); //::new 생성자 호출
        return responseList;
    }

    private final Map<Long, Post> postList = new HashMap<>(); //Long은 Post의 Id값, Post는 Class

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // Post Max Id Check - Id 값으로 Post를 구분, 중복되면 안됨
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1; // ? : 삼항연산자, keySet은 key값인 Long을 불러옴
        post.setId(maxId);

        // DB 저장
        postList.put(post.getId(), post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @PutMapping("/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        if(postList.containsKey(id)) {
            // 해당 Post 가져오기
            Post post = postList.get(id);

            // post 수정
            post.update(requestDto);
            return post.getId();
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {

        // 해당 post가 DB에 존재하는지 확인
        if(postList.containsKey(id)) {
            if(postList.get(id).getPassword().equals(requestDto.getPassword())) {
                postList.remove(id); // 해당 post 삭제하기
                return "\"success\": true";
            }

        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
        return "\"success\": false";
    }

    @GetMapping("/post/{id}")
    public Long detailPosts(@PathVariable Long id) {
        //상세 조회
        if(postList.containsKey(id)) {
            // 해당 post 가져오기(get)
            postList.get(id).getTitle();
            postList.get(id).getContents();
            postList.get(id).getAuthor();
            postList.get(id).getPassword();
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

}
