package com.gachon.UntactPhotoBooth.Configuration.Oauth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 수 있는 위치를 지정, 메서드의 파라미터로 선언된 객체로만 생성될 수 있게 함.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    // @인터페이스는 어노테이션으로 해당 LoginUser 를 사용할 수 있게 해줌.
}
