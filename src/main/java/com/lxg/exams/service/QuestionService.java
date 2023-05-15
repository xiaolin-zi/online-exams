package com.lxg.exams.service;

import com.lxg.exams.bean.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author xiaolin
* @description 针对表【question】的数据库操作Service
* @createDate 2023-03-25 13:11:18
*/
public interface QuestionService extends IService<Question> {

    //增加题目
    int addQuestion(String title, String optionA, String optionB, String optionC, String optionD,  String answer,int types,String image, String remark,int uid);



    //根据id删除题目
    int deleteQuestionById(int id);

    //根据id修改题目
    int updateQuestion(String title, String optionA, String optionB, String optionC, String optionD,  String answer,int types,String image, String remark,int uid);

    //根据id查询题目
    Question getQuestionById(int id);


    void saveQuestionBatch(MultipartFile file, QuestionService questionService,Integer uid);
}
