package com.lxg.exams.controller.usercontroller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxg.exams.bean.Question;
import com.lxg.exams.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public Boolean addQue(@RequestBody Question question, HttpSession session) {
        question.setUid((Integer) session.getAttribute("uid"));
        return questionService.save(question);
    }

    //分页查询错题
    @GetMapping("/{page}/{pageSize}")
    public Page page(@PathVariable int page, @PathVariable int pageSize, Question question, HttpSession session) {
        Integer uid = (Integer) session.getAttribute("uid");
        System.err.println(uid);
        Page quePage = new Page(page, pageSize);
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Question::getIsdeleted, 0);
        lqw.eq(Question::getUid, uid);
        lqw.eq(question.getTypes()!=null,Question::getTypes, question.getTypes());
        lqw.like(question.getTitle()!=null,Question::getTitle, question.getTitle());
        questionService.page(quePage, lqw);
        return quePage;
    }

    //修改错题
    @PutMapping
    public Boolean updateQue(@RequestBody Question question) {
        boolean b = questionService.updateById(question);
        return b;
    }

    @DeleteMapping("/{id}")
    //删除错题
    public Boolean deleteQue(@PathVariable Integer id) {
        Question question = questionService.getById(id);
        question.setIsdeleted(1);
        boolean b = questionService.updateById(question);
        return b;
    }
}

