package com.rest1.domain.post.comment.controller;

import com.rest1.domain.post.comment.dto.CommentDto;
import com.rest1.domain.post.comment.entity.Comment;
import com.rest1.domain.post.post.entity.Post;
import com.rest1.domain.post.post.service.PostService;
import com.rest1.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @GetMapping(value = "/{postId}/comments")
    public List<CommentDto> getItems(@PathVariable Long postId) {
        Post post = postService.findById(postId).get();
        return post.getComments().reversed().stream()
                .map(CommentDto::new)
                .toList();
    }

    // 댓글 단건 조회
    @GetMapping(value = "/{postId}/comments/{commentId}")
    @Transactional(readOnly = true)
    public CommentDto getItem(@PathVariable Long postId, @PathVariable Long commentId) {
        Post post = postService.findById(postId).get();
        Comment comment = post.findCommentById(commentId).get();


        return new CommentDto(comment);
    }

    // 댓글 삭제
    // restful 하진 않지만, 편의성 위해 get 사용 -> 행위 2개라 좋은 구조 x
    // deletemapping으로 변환
    @DeleteMapping("/{postId}/comments/{commentId}")
    @Transactional  // 쓰기도 하므로 readOnly 아님
    public RsData<Void> deleteItem(@PathVariable Long postId, @PathVariable Long commentId) {
        Post post = postService.findById(postId).get();
        Comment comment = post.findCommentById(commentId).get();

        postService.deleteComment(post, commentId);

        // json 형태 반환
        // 삭제된 댓글 data 필드 추가
        // void -> 데이터 없어도 됨
        return new RsData<>(
                "200-1",
                "%d번 댓글이 삭제되었습니다.".formatted(commentId)
        );
    }

    record CommentWriteReqBody(
            @NotBlank
            @Size(min = 2, max = 100)
            String content
    ) {
    }

    record CommentWriteResBody(
            CommentDto commentDto
    ) {}

    // 댓글 생성
    @PostMapping("/{postId}/comments")
    @Transactional
    public RsData<CommentWriteResBody> createItem(
            @PathVariable Long postId,
            @RequestBody @Valid CommentWriteReqBody reqBody
    ) {

        Post post = postService.findById(postId).get();
        Comment comment = postService.writeComment(post, reqBody.content);

        postService.flush();

        return new RsData<>(
                "201-1",
                "%d번 댓글이 생성되었습니다.".formatted(comment.getId()),
                new CommentWriteResBody(
                        new  CommentDto(comment)
                )
        );
    }

    // 댓글 수정
    record CommentModifyReqBody(
            @NotBlank
            @Size(min = 2, max = 100)
            String content
    ) {
    }

    @PutMapping("/{postId}/comments/{commentId}")
    @Transactional
    public RsData<Void> modifyItem(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody @Valid CommentWriteReqBody reqBody
    ) {

        Post post = postService.findById(postId).get();
        postService.modifyComment(post, commentId, reqBody.content);

        return new RsData<>(
                "200-1",
                ("%d번 댓글이 수정되었습니다.").formatted(commentId)
        );
    }
}
