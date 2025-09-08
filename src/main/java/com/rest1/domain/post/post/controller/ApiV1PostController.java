package com.rest1.domain.post.post.controller;

import com.rest1.domain.post.post.dto.PostDto;
import com.rest1.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // json 형태인 @responsebody 생략 가능
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts") // 모든 메소드에 자동 매핑
public class ApiV1PostController {

    private final PostService postService;

    @GetMapping // 주소 생략 -> 간결
    @Transactional(readOnly = true)
    public List<PostDto> list() {
        return postService.findAll().stream()
                .map(post -> new PostDto(post)) // 엔터티 하나씩 꺼내서 dto로 보내기 (구조적으로 좋음)
                .toList();
    }
}
