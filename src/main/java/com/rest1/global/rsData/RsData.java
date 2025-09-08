package com.rest1.global.rsData;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData{  // 조회를 제외한 모든 요청에 대한 응답의 필수요소 담은 클래스

    private String resultCode;
    private String msg;

    // 부가 data - comment 정보
    // Object - PostDto, CommentDto 둘 다 사용
    private Object data;

}