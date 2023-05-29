package com.lxg.exams.controller.usercontroller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxg.exams.bean.CommonResult;
import com.lxg.exams.bean.Question;
import com.lxg.exams.bean.QuestionWriteData;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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

    //导出所有公开的问题
    @GetMapping("/exportAll")
    public void exportAll(HttpServletResponse response) throws IOException {
        //查询所有公开的问题
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Question::getIspublic, 1);
        lqw.eq(Question::getIsdeleted, 0);
        List<Question> questions = questionService.list(lqw);

        //在浏览器下载导出
        String fileName = "已公开题目";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<QuestionWriteData> list = new ArrayList<>();
        for (Question question : questions) {
            QuestionWriteData questionWriteData = new QuestionWriteData();
            BeanUtils.copyProperties(question, questionWriteData);
            list.add(questionWriteData);
        }
        EasyExcel.write(response.getOutputStream(), QuestionWriteData.class)
                .sheet("题目").doWrite(list);
    }

    @GetMapping("/getData")
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
            //根据types查询出分类名称
            if (types == null) {
                question.setTypesName("暂无分类");
            } else if (types == 1) {
                question.setTypesName("计算机网络");
            } else if (types == 2) {
                question.setTypesName("操作系统");
            } else if (types == 3) {
                question.setTypesName("计算机组成原理");
            } else if (types == 4) {
                question.setTypesName("数据结构");
            } else {
                question.setTypesName("暂无分类");
            }
        }
        int count = list.size();


        if (list != null) {
            return new CommonResult<Question>(list, "查询成功", 200, count);
        } else {
            return new CommonResult<Question>(null, "查询失败", 500, count);
        }
    }

    @PostMapping("/getData")
    public CommonResult<Question> getDataByQuery(@RequestBody Question questionQuery) {
        String title = questionQuery.getTitle();
        Integer mytype = questionQuery.getTypes();
        //查询所有公开的问题
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Question::getIspublic, 1);
        lqw.eq(Question::getIsdeleted, 0);
        if (StringUtils.hasLength(title)) {
            lqw.like(Question::getTitle, title);
        }
        if (mytype != null) {
            lqw.eq(Question::getTypes, mytype);
        }
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
            //根据types查询出分类名称
            if (types == null) {
                question.setTypesName("暂无分类");
            } else if (types == 1) {
                question.setTypesName("计算机网络");
            } else if (types == 2) {
                question.setTypesName("操作系统");
            } else if (types == 3) {
                question.setTypesName("计算机组成原理");
            } else if (types == 4) {
                question.setTypesName("数据结构");
            } else {
                question.setTypesName("暂无分类");
            }
        }
        int count = list.size();


        if (list != null) {
            return new CommonResult<Question>(list, "查询成功", 200, count);
        } else {
            return new CommonResult<Question>(null, "查询失败", 500, count);
        }
    }
}
