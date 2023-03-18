package com.lxg.exams.controller.logincontroller;

import com.lxg.exams.domain.User;
import com.lxg.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther xiaolin
 * @creatr 2023/3/13 13:14
 */
@Controller
public class loginController {


    @Autowired
    private UserService userService;

    @PostMapping("/userLoginSuccess")
    public String adminLoginSuccess(String username,String password){

        User user = userService.getUser(username, password);
        if(user != null){
            return "user/adminindex";
        }else{
            return "index";
        }
    }

    @PostMapping("/userRegistSuccess")
    public String adminRegistSuccess(String username, String password, HttpServletRequest request){

        User user = userService.getUser(username, password);
        if(user!=null){
            //已被注册
            //设置消息提醒
            request.setAttribute("msg","用户名已注册！");
            return "index";
        }else{
            int i = userService.addUser(username, password);
            if(i>0){
                request.setAttribute("msg","注册成功！");
                return "index";
            }else{
                request.setAttribute("msg","注册失败！");
                return "index";
            }
        }
    }

}
