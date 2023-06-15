package com.example.spring_jh.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String contents;
    private String author;
    private String password;
}
