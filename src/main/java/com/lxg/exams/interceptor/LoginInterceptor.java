package com.lxg.exams.interceptor;

/**
 * @auther xiaolin
 * @creatr 2023/3/27 22:53
 */

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if (uid == null) {
            //未登录，返回登录页面，提示用户登录
            request.setAttribute("login_msg", "没有权限，请先登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
