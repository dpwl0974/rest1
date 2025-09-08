package com.rest1.domain.post.post.controller;

import com.rest1.domain.post.post.dto.PostDto;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.domain.post.post.service.PostService;
import com.rest1.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // json 형태인 @responsebody 생략 가능
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts") // 모든 메소드에 자동 매핑
public class ApiV1PostController {

    private final PostService postService;

    // 글 다건 조회
    @GetMapping // 주소 생략 -> 간결
    @Transactional(readOnly = true)
    public List<PostDto> getItems() {
        return postService.findAll().stream()
                .map(PostDto::new) // 엔터티 하나씩 꺼내서 dto로 보내기 (구조적으로 좋음)
                .toList();
    }

    // 글 단건 조회
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public PostDto getItem(@PathVariable Long id) {
        Post post = postService.findById(id).get();
        return new PostDto(post);
    }

    // 글 삭제 - 편의상 get => Delete 사용으로 전환 -> restful 해짐
    @DeleteMapping("/{id}")
    @Transactional
    public RsData<Void> deleteItem(@PathVariable Long id) {
        Post post = postService.findById(id).get();
        postService.delete(post);

        return new RsData<Void>(
                "204-1",
                "%d번 게시물이 삭제되었습니다.".formatted(id)
        );
    }
}
