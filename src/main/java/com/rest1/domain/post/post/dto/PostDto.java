package com.rest1.domain.post.post.dto;

import com.rest1.domain.post.post.entity.Post;

import java.time.LocalDateTime;

// lombok없이 생성자 자동 생성
// record 도입 - private 등 자동 생성으로 인해 불변성 보장
public record PostDto (
        Long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String title, // 엔터티 건드릴 필요없이 dto에서 이름 변경
        String content
){
    public PostDto(Post post){
        this(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getTitle(),
                post.getContent()
        );
    }
}
