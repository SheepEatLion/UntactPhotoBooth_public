package com.gachon.UntactPhotoBooth.Configuration.Oauth;

import com.gachon.UntactPhotoBooth.Domain.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 보안 설정 클래스 (antMatchers)
 */

@RequiredArgsConstructor
@EnableWebSecurity  // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customUserTypesOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable()  // h2-console 사용하기 위해 disable
                .and()
                .authorizeRequests()  // url 마다 옵션 설정을 하기 위한 시작점
                .antMatchers("/", "/static/css/**", "/images/**").permitAll()
                .antMatchers("/save/history").hasRole(Role.USER.name()) // 권한 관리 대상을 지정, url or http 메서드별로 관리 가능, 기본페이지는 all 했고 api 는 유저권한만 줌.
                .antMatchers("/mypage/**").hasRole(Role.USER.name())
                //.anyRequest().authenticated() // anyRequest 는 설정한 url 을 제외한 나머지 모두를 의미, authenticated 를 통해 인증된 사용자만 허용
                .and()
                .logout() // 로그아웃 설정 시작점
                .logoutSuccessUrl("/") // 로그아웃 성공시 홈으로 이동하게 함.
                .and()
                .oauth2Login() // 로그인 설정 시작점
                .userInfoEndpoint() // 로그인 성공 후, 사용자의 정보를 가져올때 설정을 담당함.
                .userService(customUserTypesOAuth2UserService); // 성공 후에 진행할 유저 서비스 인터페이스의 구현체 등록
    }
}