package com.lxg.exams.controller.logincontroller;

import com.lxg.exams.bean.User;
import com.lxg.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @auther xiaolin
 * @creatr 2023/3/13 13:14
 */
@Controller
public class loginController {


    @Autowired
    private UserService userService;

    @PostMapping("/userLoginSuccess")
    public String adminLoginSuccess(String username,String password,HttpServletRequest request,HttpSession session){

        User user = userService.getUser(username, password);
        if(user != null){
            request.setAttribute("login_msg", "登录成功！");
            session.setAttribute("uid",user.getId());
            return "user/userindex";
        }else{
            request.setAttribute("flag", false);//登录页标志
            request.setAttribute("login_msg", "账号或密码错误！");
            return "index";
        }
    }

    @PostMapping("/userRegistSuccess")
    public String adminRegistSuccess(String username, String password, String code,HttpServletRequest request, HttpSession session) {


        String token = (String) session.getAttribute("verifyCode");
        session.removeAttribute("verifyCode");


        if (token != null && token.equalsIgnoreCase(code)) {

            User user = userService.getUserByUsername(username);
            if (user != null) {
                //已被注册
                //设置消息提醒
                request.setAttribute("regist_msg", "用户名已注册！");
                request.setAttribute("flag", true);//注册页标志
                return "index";
            } else {
                int i = userService.addUser(username, password);
                if (i > 0) {
                    request.setAttribute("regist_msg", "注册成功！");
                    request.setAttribute("flag", true);//注册页标志
                    return "index";
                } else {
                    request.setAttribute("regist_msg", "注册失败！");
                    request.setAttribute("flag", true);//注册页标志
                    return "index";
                }
            }
        }else{
            // 把回显信息，保存到Request域中
            request.setAttribute("regist_msg", "验证码错误！！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("flag",true);//注册页标志
            return "index";
        }
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("uid");
    }
    //跳转到添加错题表单
    @GetMapping("form")
    public String getForm(){
        return "form";
    }
    @GetMapping("password")
    public String updatePwd() {
        return "password";
    }
    @GetMapping("upload")
    public String uploadAva(){
        return "upload";
    }
    @GetMapping("userdata")
    public String listData(){
        return "userdata";
    }
    //跳转到添加错题表单
    @GetMapping("page")
    public String page(){
        return "list";
    }
    @GetMapping("teamdata")
    public String teamdata(){
        return "teamdata";
    }
    @GetMapping("index")
    public String index(){
        return "index";
    }
}
