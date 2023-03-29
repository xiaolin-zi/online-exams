package com.lxg.exams.service.testquestion;

import com.lxg.exams.bean.Question;

import java.util.ArrayList;

public interface TestQuestionService {

    /**根据uid查询某用户指定题库里面全部的现存的题，
     * 若题数少于20道题则有多少展示多少，
     * 若大于等于20道题则随机选取20道题
     */
    ArrayList<Question> get20Question(int uid);

}
