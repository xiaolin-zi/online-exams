package com.lxg.exams.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther xiaolin
 * @creatr 2023/3/28 13:37
 */

//配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Value("${image.staticAccessPath}")
    private String staticAccessPath;


    @Value("${image.path}")
    private String basePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + basePath);
    }


    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns=new ArrayList<>();
        patterns.add("/");
        patterns.add("/user/login");
        patterns.add("/css/**");
        patterns.add("/img/**");
        patterns.add("/js/**");
        patterns.add("/kaptcha/**");
        patterns.add("/user/regist");
        registry.addInterceptor(new LoginInterceptor())
                //拦截所有请求
                .addPathPatterns("/**")
                //排除的请求
                .excludePathPatterns(patterns);
    }



}
