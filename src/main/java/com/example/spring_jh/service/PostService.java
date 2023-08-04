package com.example.spring_jh.service;

import com.example.spring_jh.dto.PostRequestDto;
import com.example.spring_jh.dto.PostResponseDto;
import com.example.spring_jh.entity.Post;
import com.example.spring_jh.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostResponseDto> getPosts() {
        // DB 조회
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        // 해당 Post가 DB에 존재하는지 확인
        Post post = findPost(id);

        if (requestDto.getPassword().equals(post.getPassword())) {
            post.update(requestDto); // post 수정하기
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
        return id;
    }

    public String deletePost(Long id, PostRequestDto requestDto) {
        // 해당 post가 DB에 존재하는지 확인
        Post post = findPost(id);

            if (requestDto.getPassword().equals(post.getPassword())) {
                postRepository.delete(post); // 해당 post 삭제하기
                return "\"success\": true";
            } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }



    public PostResponseDto detailPost(Long id, PostRequestDto requestDto){
        Post post = findPost(id);

        //상세 조회
        // Entity -> ResponseDto
        // Entity를 직접 반환하면 보안의 위험성이 있음, ResponseDto로 변환하여 반환하면 정보를 선택하여 반환 가능
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시글은 존재하지 않습니다"));
    }
}
