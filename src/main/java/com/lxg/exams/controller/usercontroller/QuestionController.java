package com.lxg.exams.controller.usercontroller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxg.exams.bean.Question;
import com.lxg.exams.bean.QuestionWriteData;
import com.lxg.exams.bean.User;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Boolean addQue(@RequestBody Question question, HttpSession session) {
        question.setId(null);
        //设置为私有
        question.setIspublic(0);
        question.setUid((Integer) session.getAttribute("uid"));
        return questionService.save(question);
    }


    //批量添加题目
    @PostMapping("/batch")
    public Map<String, Object> addBatchQue(MultipartFile file, HttpSession session) {

        Integer uid = (Integer) session.getAttribute("uid");

        //上传过来的excel文件
        questionService.saveQuestionBatch(file, questionService, uid);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "上传成功");
        return map;
    }

    //导出当前页题目为excel
    @PostMapping("/exportPage")
    public void exportPageQuestion(@RequestBody List<Question> questions, HttpServletResponse response) throws IOException {



        System.out.println(questions+"====================");

        //在浏览器下载导出
        String fileName = "当前页题目";
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

    //导出题目为excel
    @GetMapping("/exportAll")
    public void exportQuestion(HttpSession session, HttpServletResponse response) throws IOException {
        Integer uid = (Integer) session.getAttribute("uid");
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Question::getUid, uid);
        lqw.eq(Question::getIsdeleted, 0);
        lqw.orderByDesc(Question::getCreateTime);
        List<Question> questions = questionService.list(lqw);
        //在浏览器下载导出
        String fileName = "题目";
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
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "导出成功");
//        return map;
    }


    //分页查询错题
    @GetMapping("/{page}/{pageSize}")
    public Page page(@PathVariable int page, @PathVariable int pageSize, Question question, HttpSession session) {
        Integer uid = (Integer) session.getAttribute("uid");
        Page quePage = new Page(page, pageSize);
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Question::getUid, uid);
        lqw.eq(Question::getIsdeleted, 0);
        lqw.eq(question.getTypes() != null, Question::getTypes, question.getTypes());
        lqw.eq(question.getRank() != null, Question::getRank, question.getRank());
        lqw.like(question.getTitle() != null, Question::getTitle, question.getTitle());
        questionService.page(quePage, lqw);
        return quePage;
    }

    //修改错题
    @PutMapping
    public Boolean updateQue(@RequestBody Question question) {
        //设置更新时间
        //获取当前格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        String updateTime = sdf.format(new Date());
        question.setUpdateTime(updateTime);
        boolean b = questionService.updateById(question);
        return b;
    }

    @DeleteMapping("/{id}")
    //删除错题
    public Boolean deleteQue(@PathVariable Integer id) {
        Question question = questionService.getById(id);
        question.setIsdeleted(1);
        System.out.println(question + "=====================");
        boolean b = questionService.updateById(question);
        return b;
    }


    @PostMapping("/public/{id}")
    //设置题目为公开或者私有
    public Boolean setQuestionPublicById(@PathVariable Integer id) {
        Question question = questionService.getById(id);
        Integer ispublic = question.getIspublic();
        if (ispublic == 1) {
            question.setIspublic(0);
        } else {
            question.setIspublic(1);
            //贡献值加1
            Integer uid = question.getUid();
            User user = userService.getById(uid);
            Integer contribution = user.getContributions();
            user.setContributions(contribution + 1);
            userService.updateById(user);
        }
        boolean b = questionService.updateById(question);
        return b;
    }


    //根据id查询题目是否已经公开
    @GetMapping("/status/{id}")
    public Boolean getQuestionPublicById(@PathVariable Integer id) {
        Question question = questionService.getById(id);
        Integer ispublic = question.getIspublic();
        if (ispublic == 1) {
            return true;
        } else {
            return false;
        }
    }


    //根据uid和title查询题目是否存在
    @PostMapping("/isExist")
    public Boolean getQuestionByUidAndTitle(@RequestBody Question question, HttpSession session) {

        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        Integer uid = (Integer) session.getAttribute("uid");
        lqw.eq(Question::getUid, uid);
        lqw.eq(Question::getTitle, question.getTitle());
        lqw.eq(Question::getOptiona, question.getOptiona());
        lqw.eq(Question::getOptionb, question.getOptionb());
        lqw.eq(Question::getOptionc, question.getOptionc());
        lqw.eq(Question::getOptiond, question.getOptiond());
        lqw.eq(Question::getAnswer, question.getAnswer());
        lqw.eq(Question::getTypes, question.getTypes());
        lqw.eq(Question::getIsdeleted, question.getIsdeleted());
        if (question.getImage() == null) {
            lqw.isNull(Question::getImage);
        } else {
            lqw.eq(Question::getImage, question.getImage());
        }

        Question question1 = questionService.getOne(lqw);
        System.out.println(question1 + "=====================================");
        if (question1 != null) {
            return true;
        } else {
            return false;
        }
    }
/*
    @PostMapping("/update/{rank}")
    public Boolean updateRank(@PathVariable Integer rank,HttpSession session){
        LambdaQueryWrapper<Question> lqw=new LambdaQueryWrapper<>();
        Integer uid = (Integer) session.getAttribute("uid");
        lqw.eq(Question::getUid,uid);
        lqw.eq(Question::getRank,rank);
        questionService.updateById()
        return questionService.update(lqw);
    }*/
}

