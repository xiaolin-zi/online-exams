package com.lxg.exams.mapper;

import com.lxg.exams.bean.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

/**
* @author xiaolin
* @description 针对表【question】的数据库操作Mapper
* @createDate 2023-03-25 13:11:18
* @Entity com.lxg.exams.bean.Question
*/
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    //根据uid查询改用户现存的错题（未删除的）
    @Select("select * from question where uid=#{uid} and isDeleted = 0}")
    public ArrayList<Question> getAllQuestionByUid(int uid);

    //根据uid和qid查询指定的错题
    @Select("select * from question where uid=#{uid} and id = #{id}")
    public Question getQuestionByUidQid(int uid,int qid);








}




