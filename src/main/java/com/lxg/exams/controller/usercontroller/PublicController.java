package com.lxg.exams.controller.usercontroller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxg.exams.bean.CommonResult;
import com.lxg.exams.bean.Question;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @auther xiaolin
 * @creatr 2023/4/2 1:01
 */

//逛一逛controller
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public QuestionService questionService;

    @Autowired
    public UserService userService;

    @RequestMapping("/getData")
    public CommonResult<Question> getData() {
        //查询所有公开的问题
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Question::getIspublic, 1);
        lqw.eq(Question::getIsdeleted, 0);
        List<Question> list = questionService.list(lqw);
        //遍历list
        for (Question question : list) {
            //根据uid查询出用户名和头像
            Integer uid = question.getUid();
            String username = userService.getById(uid).getUsername();
            String avatar = userService.getById(uid).getAvatar();
            question.setUsername(username);
            question.setUserAvatar(avatar);

            Integer types = question.getTypes();
            if (types == 1) {
                question.setTypesName("计算机网络");
            } else if (types == 2) {
                question.setTypesName("操作系统");
            } else if (types == 3) {
                question.setTypesName("计算机组成原理");
            } else if (types == 4) {
                question.setTypesName("数据结构");
            }
        }
        int count = list.size();


        if (list != null) {
            return new CommonResult<Question>(list, "查询成功", 200, count);
        }else {
            return new CommonResult<Question>(null, "查询失败", 500, count);
        }
    }
}
