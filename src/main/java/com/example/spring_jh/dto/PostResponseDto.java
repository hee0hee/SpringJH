package com.example.spring_jh.dto;

import com.example.spring_jh.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String Title;
    private String contents;
    private String author;
    private String password;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.Title = post.getTitle();
        this.contents = post.getContents();
        this.author = post.getAuthor();
        this.password = post.getPassword();
    }
}

