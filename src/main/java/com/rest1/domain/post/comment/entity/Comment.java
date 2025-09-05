package com.rest1.domain.post.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity {

    private String content;
    @ManyToOne
    //@Getter(AccessLevel.PRIVATE) // getter가 있지만 private라 안됨 -> 즉, post 무시 -> 하지만 필요할 때 못 씀
    @JsonIgnore // json 무시해라 -> getter.private 보다 나은 방법 -> 즉, 순환 참조 고리 끊기
    private Post post;

    public void update(String content) {
        this.content = content;
    }
}
