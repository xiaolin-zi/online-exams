package com.lxg.exams.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lxg.exams.bean.Question;
import com.lxg.exams.bean.QuestionData;
import com.lxg.exams.exceptionhandler.MyException;
import com.lxg.exams.service.QuestionService;

/**
 * @auther xiaolin
 * @creatr 2023/5/15 8:44
 */
public class QuestionExcelListener extends AnalysisEventListener<QuestionData> {

    public QuestionService questionService;

    public Integer uid;

    public QuestionExcelListener(){}


    public QuestionExcelListener(QuestionService questionService,Integer uid) {
        this.questionService = questionService;
        this.uid = uid;
    }

    @Override
    public void invoke(QuestionData questionData, AnalysisContext analysisContext) {
        if (questionData==null){
            throw new MyException(20001,"文件数据为空");
        }
        //一行一行读取
        Question question = new Question();
        question.setTitle(questionData.getTitle());
        question.setOptiona(questionData.getOptiona());
        question.setOptionb(questionData.getOptionb());
        question.setOptionc(questionData.getOptionc());
        question.setOptiond(questionData.getOptiond());
        question.setAnswer(questionData.getAnswer());
        question.setTypes(questionData.getType());
        question.setRemark(questionData.getRemark());
        question.setUid(uid);
        questionService.save(question);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
