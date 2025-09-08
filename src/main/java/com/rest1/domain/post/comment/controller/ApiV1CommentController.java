package com.rest1.domain.post.comment.controller;

import com.rest1.domain.post.comment.dto.CommentDto;
import com.rest1.domain.post.comment.entity.Comment;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // json 형태인 @responsebody 생략 가능
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts") // 모든 메소드에 자동 매핑
public class ApiV1CommentController {
    private final PostService postService;

    // 댓글 다건 조회
    @GetMapping("/{postId}/comments")
    public List<CommentDto> getItems(@PathVariable Long postId) {
        Post post = postService.findById(postId).get();
        return post.getComments().stream()
                .map(CommentDto::new)
                .toList();
    }

    // 댓글 단건 조회
    @GetMapping("/{postId}/comments/{commentId}")
    @Transactional(readOnly = true)
    public CommentDto getItem(@PathVariable Long postId, @PathVariable Long commentId) {
        Post post = postService.findById(postId).get();
        Comment comment = post.findCommentById(commentId).get();
        return new CommentDto(comment);
    }

    // 댓글 삭제
    // restful 하진 않지만, 편의성 위해 get 사용 -> 행위 2개라 좋은 구조 x
    @GetMapping("/{postId}/comments/{commentId}/delete")
    @Transactional  // 쓰기도 하므로 readOnly 아님
    public String deleteItem(@PathVariable Long postId, @PathVariable Long commentId) {
        Post post = postService.findById(postId).get();
        postService.deleteComment(post, commentId);

        return "%d번 댓글 삭제 완료".formatted(commentId);
    }
}
