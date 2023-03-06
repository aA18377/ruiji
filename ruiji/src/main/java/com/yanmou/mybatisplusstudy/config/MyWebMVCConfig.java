package com.yanmou.mybatisplusstudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/23 - 02 - 23 - 22:14
 * @Description: com.yanmou.mybatisplusstudy.config
 * @version: 1.0
 */
@Configuration
public class MyWebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceport()).addPathPatterns("/**")
                .excludePathPatterns("/static/**/**.png")
                .excludePathPatterns("/static/**/**.jpg")
                .excludePathPatterns("/static/**/**.js")
                .excludePathPatterns("/static/**/**.css")
                .excludePathPatterns("/static/**.ico")
                .excludePathPatterns("/employee/toLogin")
                .excludePathPatterns("/employee/login")

                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/sendMsg")
                .excludePathPatterns("/user/loginView");
    }

}
