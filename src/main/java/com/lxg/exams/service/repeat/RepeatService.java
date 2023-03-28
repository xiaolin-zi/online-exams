package com.lxg.exams.service.repeat;


import com.lxg.exams.bean.Question;

import java.util.ArrayList;

public interface RepeatService {

    //在错题重做中根据uid查询该用户的全部现存在的错题
    ArrayList<Question> getWrongQuestion(int uid);


    //在上面的基础中获取单个错题的qid并查询
    Question getQuestionByPid(int uid,int qid);



}
