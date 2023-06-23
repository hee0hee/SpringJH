package com.example.spring_jh.service;

import com.example.spring_jh.dto.PostRequestDto;
import com.example.spring_jh.dto.PostResponseDto;
import com.example.spring_jh.entity.Post;
import com.example.spring_jh.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    private final Map<Long, Post> postList = new HashMap<>(); //Long은 Post의 Id값, Post는 Class

    public List<PostResponseDto> getPosts() {
        // Map To List
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList(); //::new 생성자 호출
        // DB 조회
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getPostsByKeyword (String keyword) {
        return postRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
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

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        // 해당 Post가 DB에 존재하는지 확인
        if(postList.containsKey(id)) {
            // 해당 Post 가져오기
            if(postList.get(id).getPassword().equals(requestDto.getPassword())) {
                Post post = postList.get(id);
                // post 수정
                post.update(requestDto);
                return post.getId();
            }
        } else {
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
        return id;
    }

    public String deletePost(Long id, PostRequestDto requestDto) {
        // 해당 post가 DB에 존재하는지 확인
        if(postList.containsKey(id)) {
            if(postList.get(id).getPassword().equals(requestDto.getPassword())) {
                postList.remove(id); // 해당 post 삭제하기
                return "\"success\": true";
            }

        } else {
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
        return "\"success\": false";
    }
}
