package com.gachon.UntactPhotoBooth.Domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 유저 역할 이넘클래스
 */

@Getter
@RequiredArgsConstructor
public enum Role {
    // 스프링 시큐리티에서는 권한에 항상 ROLE_ 이 붙어야한다.
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}