package com.rest1.global.rsData;


import com.rest1.domain.post.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {  // 조회를 제외한 모든 요청에 대한 응답의 필수요소 담은 클래스

    private String resultCode;
    private String msg;
    private CommentDto data; // 부가 data - comment 정보

}