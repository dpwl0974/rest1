package com.rest1.global.globalExceptionHandler;


import com.rest1.global.rsData.RsData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;


@ControllerAdvice // 컨트롤러 보다 예외 발생했을 때 함수 실행
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public RsData<Void> handleException(){
        return new RsData<Void>(
                "404-1",
                "존재하지 않는 데이터입니다."
        );
    }

}