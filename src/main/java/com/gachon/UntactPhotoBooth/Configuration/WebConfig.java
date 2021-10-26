package com.gachon.UntactPhotoBooth.Configuration;

import com.gachon.UntactPhotoBooth.Configuration.Oauth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserArgumentResolver);
        //HandlerMethodArgumentResolver 는 항상 addArgumentResolvers() 를 통해서 구현해야한다. 다른 리졸버가 필요할때도 이렇게 만든다.
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}