package com.rest1.domain.post.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // json 형태인 @responsebody 생략 가능
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts") // 모든 메소드에 자동 매핑
public class ApiV1CommentController {
}
