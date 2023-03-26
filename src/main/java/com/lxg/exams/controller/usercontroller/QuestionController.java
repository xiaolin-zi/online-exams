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
    public Boolean addQue(@RequestBody Question question, HttpSession session){
        question.setUid((Integer) session.getAttribute("uid"));
         return questionService.save(question);
    }

    //分页查询错题
    @GetMapping("/page")
    public Page page(int page, int pageSize, String title){
        Page<Question> quePage=new Page<>(page,pageSize);
        LambdaQueryWrapper<Question> lqw=new LambdaQueryWrapper<>();
        lqw.like(title!=null,Question::getTitle,title);
        questionService.page(quePage,lqw);
        return quePage;
    }

    //修改错题
    @PutMapping
    public Boolean updateQue(@RequestBody Question question){
        boolean b = questionService.updateById(question);
        return b;
    }

    @DeleteMapping("/{id}")
    //删除错题
    public void deleteQue(Integer id){
        Question question = questionService.getById(id);
        question.setIsdeleted(1);
    }
}

