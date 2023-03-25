package com.lxg.exams.controller.usercontroller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxg.exams.bean.Question;
import com.lxg.exams.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @auther 薛思暖
 * @creatr 2023/3/13 13:14
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @PostMapping
    public Boolean addQue(@RequestBody Question question){
        //question.setUid((Integer) session.getAttribute("uid"));
        question.setUid(1);
        return questionService.save(question);
    }


    @GetMapping("/page")
    public Page page(int page, int pageSize, String title){
        Page<Question> quePage=new Page<>(page,pageSize);
        LambdaQueryWrapper<Question> lqw=new LambdaQueryWrapper<>();
        lqw.like(title!=null,Question::getTitle,title);
        questionService.page(quePage,lqw);
        /*List<Question> list = questionService.list(lqw);
        quePage.setRecords(list);*/
        return quePage;
    }


}

