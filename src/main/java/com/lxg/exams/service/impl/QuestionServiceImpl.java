package com.lxg.exams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxg.exams.bean.Question;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xiaolin
* @description 针对表【question】的数据库操作Service实现
* @createDate 2023-03-25 13:11:18
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    //注入mapper
    @Autowired
    public QuestionMapper questionMapper;




    //增加题目
    @Override
    public int addQuestion(String title, String optionA, String optionB, String optionC, String optionD, String answer, int types, String image, String remark, int uid) {
        //id自增，不需要传入
        //isDelete默认为0，不需要传入
        //isPublic默认为0，不需要传入
        Question question = new Question(null,title,optionA,optionB,optionC,optionD,answer,types,image,remark,null,uid,null,null,null,null,null);
        int insert = questionMapper.insert(question);
        return insert;
    }

    //根据id删除题目
    @Override
    public int deleteQuestionById(int id) {

        int i = questionMapper.deleteById(id);
        return i;
    }

    //根据id修改题目
    @Override
    public int updateQuestion(String title, String optionA, String optionB, String optionC, String optionD,  String answer,int types,String image, String remark,int uid) {
        Question question = new Question(null,title,optionA,optionB,optionC,optionD,answer,types,image,remark,null,uid,null,null,null,null,null);
        int i = questionMapper.updateById(question);
        return i;
    }

    //根据id查询题目
    @Override
    public Question getQuestionById(int id) {
        Question question = questionMapper.selectById(id);
        return question;
    }





}




