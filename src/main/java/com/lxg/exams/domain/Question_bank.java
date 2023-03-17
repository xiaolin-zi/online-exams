package com.lxg.exams.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value ="question_bank")
public class Question_bank implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer qid;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer types;


}
