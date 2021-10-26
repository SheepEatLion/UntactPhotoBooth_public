package com.gachon.UntactPhotoBooth.Controller;


import lombok.Getter;
import lombok.Setter;

/**
 *
 * API 공통 응답 값
 */

@Getter
@Setter
public class CommonResult {
    private boolean isSuccess;      // API 호출 성공여부
    private String code;            // 응답코드
    private String message;         // 응답 메시지
}
