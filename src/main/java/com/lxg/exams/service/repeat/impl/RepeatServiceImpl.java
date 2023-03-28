package com.lxg.exams.service.repeat.impl;

import com.lxg.exams.bean.Question;
import com.lxg.exams.mapper.QuestionMapper;
import com.lxg.exams.mapper.UserMapper;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.service.repeat.RepeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RepeatServiceImpl implements RepeatService {

    @Autowired
    QuestionMapper questionMapper;

    //在错题重做中根据uid查询该用户的全部现存在的错题
    @Override
    public ArrayList<Question> getWrongQuestion(int uid) {
        ArrayList<Question> allQuestionByUid = questionMapper.getAllQuestionByUid(uid);
        return allQuestionByUid;
    }


    //在上面的基础中获取单个错题的qid并查询
    @Override
    public Question getQuestionByPid(int uid,int qid) {
        Question questionByUidQid = questionMapper.getQuestionByUidQid(uid, qid);
        return questionByUidQid;
    }
}
