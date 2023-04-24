package com.lxg.exams.controller.usercontroller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxg.exams.bean.Question;
import com.lxg.exams.bean.User;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther xiaolin
 * @creatr 2023/3/13 13:14
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @GetMapping
    public String getUid(HttpSession session) {
        int uid = (Integer) session.getAttribute("uid");
        User user = userService.getById(uid);
        return user.getAvatar();
    }

    @ResponseBody
    @PutMapping("/{avatar}")
    public Boolean updateImg(@PathVariable String avatar, HttpSession session) {
        int uid = (Integer) session.getAttribute("uid");
        User user = userService.getById(uid);
        user.setAvatar(avatar);
        boolean b = userService.updateById(user);
        return b;
    }

    //登录
    @PostMapping("/login")
    public String adminLoginSuccess(String username, String password, HttpServletRequest request, HttpSession session) {

        User user = userService.getUser(username, password);
        System.out.println(user);
        if (user != null) {
            request.setAttribute("login_msg", "登录成功！");
            session.setAttribute("user", user);
            session.setAttribute("uid", user.getUid());
//            return "user/userindex";
            return "redirect:/user/index";
        } else {
//            request.setAttribute("flag", false);//登录页标志
            request.setAttribute("login_msg", "账号或密码错误！");
            return "index";
        }


    }

    //注册
    @PostMapping("/regist")
    public String adminRegistSuccess(String username, String password, String code, HttpServletRequest request, HttpSession session) {
        String token = (String) session.getAttribute("verifyCode");
        session.removeAttribute("verifyCode");
        if (token != null && token.equalsIgnoreCase(code)) {

            User user = userService.getUserByUsername(username);
            if (user != null) {
                //已被注册
                //设置消息提醒
                request.setAttribute("regist_msg", "用户名已注册！");
//                request.setAttribute("flag", true);//注册页标志
                return "index";
            } else {

                int i = userService.addUser(username, password);

                if (i > 0) {
                    request.setAttribute("regist_msg", "注册成功！");
//                    request.setAttribute("registSuccess",true);
//                    request.setAttribute("flag", true);//注册页标志
//                    return "index";
                    User user1 = userService.getUserByUsername(username);
                    session.setAttribute("uid", user1.getUid());
                    session.setAttribute("user", user1);
                    return "redirect:/user/index";
                } else {
                    request.setAttribute("regist_msg", "注册失败！");
//                    request.setAttribute("flag", true);//注册页标志
                    return "index";
                }
            }
        } else {
            // 把回显信息，保存到Request域中
            request.setAttribute("regist_msg", "验证码错误！！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("flag", true);//注册页标志
            return "index";
        }
    }


    /**
     * 退出
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("uid");
        return "index";
    }

    //跳转到添加错题表单
    @GetMapping("/form")
    public String getForm() {
        return "form";
    }

    //跳转到添加错题表单
    @GetMapping("/form2")
    public String getForm2() {
        return "form2";
    }


    //验证密码是否正确
    @ResponseBody
    @GetMapping("/checkPassword/{value}")
    public boolean checkPassword(@PathVariable String value, HttpSession session, HttpServletRequest request) {
        //获取当前登录用户的id
        int uid = (Integer) session.getAttribute("uid");
        User user = userService.getById(uid);
        System.out.println(value + "====================" + user.getPassword() + "====================");
        if (user.getPassword().equals(value)) {
            System.out.println("密码正确");
            return true;
        } else {
            return false;
        }
    }

    //修改密码
    @ResponseBody
    @PutMapping("/updatePassword/{password}")
    public boolean updatePassword(@PathVariable String password, HttpSession session, HttpServletRequest request) {
        //获取当前登录用户的id
        int uid = (Integer) session.getAttribute("uid");
        User user = userService.getById(uid);
        user.setPassword(password);
        boolean b = userService.updateById(user);
        return b;
    }


    //跳转到修改头像
    @GetMapping("/upload")
    public String uploadAva() {
        return "upload";
    }

    //跳转到查看个人资料
    @GetMapping("/userdata")
    public String listData() {
        return "userdata";
    }


    //跳转到查看错题
    @GetMapping("/page")
    public String page() {
        return "user/list";
    }

    //跳转到查看公开错题
    @GetMapping("/public")
    public String topublic() {
        return "user/public";
    }


    //跳转到查看团队信息
    @GetMapping("/teamdata")
    public String teamdata() {
        return "teamdata";
    }

    //用户首页
    @GetMapping("/index")
    public String index() {
        return "user/userindex";
    }

    //用户首页展示内容
    @GetMapping("/note")
    public String note() {
        return "user/note";
    }


    //错题测试
    @GetMapping("/test")
    public String getTest() {
        return "user/test";
    }


    //跳转到登录页面
    @GetMapping("/tologin")
    public String toLogin() {
        return "index";
    }


    @GetMapping("/updatepassword")
    public String updatePassword() {
        return "updatepassword";
    }


    //用户首页展示内容数据
    @GetMapping("/getIndexData")
    @ResponseBody
    public Map<String, Object> getIndexData(HttpSession session) {
        int uid = (Integer) session.getAttribute("uid");
        User user = userService.getById(uid);
        Map<String, Object> map = new HashMap<>();
        //查询当前用户的错题数量
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Question::getUid, uid);
        lqw.eq(Question::getIsdeleted, 0);
        int totalNum = (int) questionService.count(lqw);
        map.put("totalNum", totalNum);
        //查询当前用户今日新增的错题数量
        LambdaQueryWrapper<Question> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(Question::getUid, uid);
        lqw2.eq(Question::getIsdeleted, 0);
        //获取今天的日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);
        lqw2.ge(Question::getUpdateTime, today);
        int dateNum = (int) questionService.count(lqw2);
        map.put("dateNum", dateNum);
        //查询当前系统用户的数量
        int userNum = (int) userService.count();
        map.put("userNum", userNum);
        //查询当前用户的贡献值
        int contribution = user.getContributions();
        map.put("contribution", contribution);
        //查询贡献值前10的用户
        List<User> userList = userService.list(new QueryWrapper<User>().orderByDesc("contributions").last("limit 10"));
        map.put("userList", userList);

        //查询当前用户的排名
        //先查询比当前用户的贡献值大的用户数量
        int count = (int) userService.count(new QueryWrapper<User>().gt("contributions", contribution));
        //+1就是当前用户的排名
        map.put("ranking", count + 1);

        //查询榜首的用户贡献值
        Integer topContribution = userList.get(0).getContributions();
        map.put("topContribution", topContribution);

        //状态码
        map.put("code", 200);
        return map;
    }

    //给用户添加贡献值
    @PostMapping("/addContribution")
    @ResponseBody
    public Map<String, Object> addContribution(@RequestBody Question question,HttpSession session) {
        System.out.println(question);
        //获取题目所有者的id
        Integer uid = question.getUid();
        //根据uid查询用户
        User user = userService.getById(uid);
        //获取用户的贡献值
        Integer contribution = user.getContributions();
        contribution+=1;
        user.setContributions(contribution);
        //更新用户
        userService.updateById(user);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return map;
    }
}
