package com.sparta.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String username;
    private String password;
    private String contents;
}
