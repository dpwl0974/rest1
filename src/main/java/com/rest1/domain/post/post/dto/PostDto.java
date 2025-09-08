package com.rest1.domain.post.post.dto;

import com.rest1.domain.post.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class PostDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String subject; // 엔터티 건드릴 필요없이 dto에서 이름 변경
    private String body;

    public PostDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.subject = post.getTitle();
        this.body = post.getContent();
    }
}
