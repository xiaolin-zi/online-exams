package com.lxg.exams.service.testquestion.impl;

import com.lxg.exams.bean.Question;
import com.lxg.exams.mapper.QuestionMapper;
import com.lxg.exams.service.testquestion.TestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;

@Service
public class TestQuestionImpl implements TestQuestionService {

    @Autowired
    QuestionMapper questionMapper;

    /**根据uid查询某用户指定题库里面全部的现存的题，
     * 若题数少于20道题则有多少展示多少，
     * 若大于等于20道题则随机选取20道题
     */
    @Override
    public ArrayList<Question> get20Question(int uid) {
        int num=0;
        Random random = new Random();
        ArrayList<Question> A;
        ArrayList<Question> allQuestionByUid = questionMapper.getAllQuestionByUid(uid);
        //查询的题目的总数
        int size = allQuestionByUid.size();
        int[] ints = new int[size];
        if(size>=20){
            //获取现在数组里面有多少个数字
            for(int j=0;j<ints.length;j++){
                if(ints[j]!=0){
                    num++;
                }
            }
            for(int k=0;k<num;k++){
                //随机获取一个数
                int i = random.nextInt(size);
                for(int n=0;n<num;){
                    if(i!=ints[n]){
                        n++;
                    }else{
                        break;
                    }

                }
            }

        }else{
            return allQuestionByUid;
        }
        return null;
    }
}
