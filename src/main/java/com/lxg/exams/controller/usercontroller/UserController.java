package com.lxg.exams.controller.usercontroller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxg.exams.bean.Question;
import com.lxg.exams.bean.RankData;
import com.lxg.exams.bean.User;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @GetMapping("/getUser")
    public User getUser(HttpSession session) {
        int uid = (Integer) session.getAttribute("uid");
        User user = userService.getById(uid);
        return user;
    }

    //获取用户已公开和总共的题目数量
    @ResponseBody
    @GetMapping("/getQuestionCount")
    public Map<String, Integer> getQuestionCount(HttpSession session) {
        int uid = (Integer) session.getAttribute("uid");
        //根据用户id获取用户的所有题目
        List<Question> questions = questionService.list(new QueryWrapper<Question>().eq("uid", uid));
        //遍历所有题目，获取已公开的题目数量
        int publicCount = 0;
        for (Question question : questions) {
            if (question.getIspublic() == 1) {
                publicCount++;
            }
        }
        //将已公开和总共的题目数量存入map中
        Map<String, Integer> map = new HashMap<>();
        map.put("publicCount", publicCount);
        map.put("totalCount", questions.size());
        return map;
    }


    //获取用户不同天新增的题目数量
    @ResponseBody
    @GetMapping("/getQuestionCountByDate")
    public List<Map<String, Object>> getQuestionCountByDate(HttpSession session) {
        int uid = (Integer) session.getAttribute("uid");
        List<Map<String, Object>> map = questionService.getQuestionCountByDate(uid);
        return map;
    }

    //获取每种熟悉度的题目数量
    @ResponseBody
    @GetMapping("getRankData")
    public List<RankData> getRankData(HttpSession session){
        List<RankData> list = new ArrayList<>();
        //查询
        QueryWrapper<Question> wrapper = new QueryWrapper<>();

        //获取用户id
        int uid = (Integer) session.getAttribute("uid");
        wrapper.eq("uid",uid);
        wrapper.eq("rank",1);
        long count = questionService.count(wrapper);

        QueryWrapper<Question> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("uid",uid);
        wrapper2.eq("rank",2);
        long count2 = questionService.count(wrapper2);

        QueryWrapper<Question> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("uid",uid);
        wrapper3.eq("rank",3);
        long count3 = questionService.count(wrapper3);

        list.add(new RankData("陌生", (int) count));
        list.add(new RankData("懵懂", (int) count2));
        list.add(new RankData("熟悉", (int) count3));
        return list;

    }


    //获取用户每种类型的题目数量
    @ResponseBody
    @GetMapping("/getPercentage")
    public Map<String, Integer> getPercentage(HttpSession session) {
        int uid = (Integer) session.getAttribute("uid");
        //根据用户id获取用户的所有题目
        List<Question> questions = questionService.list(new QueryWrapper<Question>().eq("uid", uid));
        //遍历所有题目，获取每种类型的题目数量
        int jsjwl = 0;
        int czxt = 0;
        int jsjzcyl = 0;
        int sjjg = 0;
        for (Question question : questions) {
            if (question.getTypes()!=null&&question.getTypes()==1) {
                jsjwl++;
            } else if (question.getTypes()!=null&&question.getTypes()==2) {
                czxt++;
            } else if (question.getTypes()!=null&&question.getTypes()==3) {
                jsjzcyl++;
            } else if (question.getTypes()!=null&&question.getTypes()==4) {
                sjjg++;
            }
        }
        //将每种类型的题目数量存入map中
        Map<String, Integer> map = new HashMap<>();
        map.put("jsjwl", jsjwl);
        map.put("czxt", czxt);
        map.put("jsjzcyl", jsjzcyl);
        map.put("sjjg", sjjg);
        int total = jsjwl + czxt + jsjzcyl + sjjg;
        map.put("total",total );
        return map;
    }



    //修改个人资料
    @ResponseBody
    @PostMapping("/updateUser")
    public boolean updateUser(@RequestBody User user, HttpSession session) {
        System.out.println("user:"+user);

        int uid = (Integer) session.getAttribute("uid");
        user.setUid(uid);
        boolean b = userService.updateById(user);
        return b;
    }

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

    //跳转到个人资料
    @GetMapping("/profile")
    public String data() {
        return "user/profile";
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
    @GetMapping("/rewrite")
    public String getTest() {
        return "user/rewriteList";
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
        lqw2.ge(Question::getCreateTime, today);
        int dateNum = (int) questionService.count(lqw2);
        map.put("dateNum", dateNum);
        //查询当前系统用户的数量
        int userNum = (int) userService.count();
        map.put("userNum", userNum);
        //查询当前用户的贡献值
        int contribution = user.getContributions();
        map.put("contribution", contribution);
        //查询贡献值前10的用户
//        List<User> userList = userService.list(new QueryWrapper<User>().orderByDesc("contributions").last("limit 10"));
        List<User> userList = userService.list(new QueryWrapper<User>().orderByDesc("contributions"));

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
    public Map<String, Object> addContribution(@RequestBody Question question, HttpSession session) {
        System.out.println(question);
        //获取题目所有者的id
        Integer uid = question.getUid();
        //根据uid查询用户
        User user = userService.getById(uid);
        //获取用户的贡献值
        Integer contribution = user.getContributions();
        contribution += 1;
        user.setContributions(contribution);
        //更新用户
        userService.updateById(user);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return map;
    }


    //根据用户名查询其排名
    @GetMapping("/getRankingByUserName")
    @ResponseBody
    public Map<String, Object> getRankingByUserName(String username) {
        List<User> userList = new ArrayList<>();

        userList = userService.list(new QueryWrapper<User>().like("username", username));


        Map<String, Object> map = new HashMap<>();


        map.put("userList", userList);
        //状态码
        map.put("code", 200);
        return map;
    }


}
