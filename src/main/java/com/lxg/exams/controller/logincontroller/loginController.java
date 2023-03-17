package com.lxg.exams.controller.logincontroller;

import com.lxg.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @auther xiaolin
 * @creatr 2023/3/13 13:14
 */
@Controller
public class loginController {


    @Autowired
    private UserService userService;

    @PostMapping("/adminLoginSuccess")
    public String adminLoginSuccess(String username,String password){
        User user = userService.getUser(username, password);
        if(user != null){
            return "admin/adminindex";
        }else{
            return "index";
        }
    }
}
