package com.lxg.exams.controller.usercontroller;

import com.lxg.exams.bean.User;
import com.lxg.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @auther xiaolin
 * @creatr 2023/3/13 13:14
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;





    //登录
    @PostMapping("/login")
    public String adminLoginSuccess(String username,String password,HttpServletRequest request,HttpSession session){

        User user = userService.getUser(username, password);
        System.out.println(user);
        if(user != null){
            request.setAttribute("login_msg", "登录成功！");
            session.setAttribute("user",user);
            session.setAttribute("uid",user.getUid());
            return "user/userindex";
        }else{
            request.setAttribute("flag", false);//登录页标志
            request.setAttribute("login_msg", "账号或密码错误！");
            return "index";
        }



    }

    //注册
    @PostMapping("/regist")
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
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("uid");
        return "index";
    }

    //跳转到添加错题表单
    @GetMapping("/form")
    public String getForm(){
        return "/form";
    }
    //跳转到添加错题表单
    @GetMapping("/form2")
    public String getForm2(){
        return "/form2";
    }



    //跳转到修改密码
    @GetMapping("/password")
    public String updatePwd() {
        return "password";
    }


    //跳转到修改头像
    @GetMapping("/upload")
    public String uploadAva(){
        return "upload";
    }

    //跳转到查看个人资料
    @GetMapping("/userdata")
    public String listData(){
        return "userdata";
    }


    //跳转到查看错题
    @GetMapping("/page")
    public String page(){
        return "list";
    }

    //跳转到查看团队信息
    @GetMapping("/teamdata")
    public String teamdata(){
        return "teamdata";
    }

    //用户首页
    @GetMapping("/index")
    public String index(){
        return "/user/userindex";
    }

    //用户首页展示内容
    @GetMapping("/note")
    public String note(){
        return "/user/note";
    }


    //错题测试
    @GetMapping("/test")
    public String getTest(){
        return "/user/test";
    }


    //跳转到登录页面
    @GetMapping("/tologin")
    public String toLogin(){
        return "index";
    }


    @GetMapping("/updatepassword")
    public String updatePassword(){
        return "updatepassword";
    }
}
