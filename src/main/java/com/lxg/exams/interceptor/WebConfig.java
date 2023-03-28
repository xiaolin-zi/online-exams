package com.lxg.exams.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther xiaolin
 * @creatr 2023/3/28 13:37
 */

//配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //拦截所有请求
                .addPathPatterns("/**")
                //排除的请求
                .excludePathPatterns("/index.html","/user/login","/","/css/**","/js/**","/img/**");
    }



}
