package com.lxg.exams.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @auther xiaolin
 * @creatr 2023/5/15 8:35
 */
//与excel表格对应的实体类
@Data
public class QuestionWriteData {


    @ExcelProperty("题目")
    private String title;

    @ExcelProperty("选项A")
    private String optiona;

    @ExcelProperty("选项B")
    private String optionb;

    @ExcelProperty("选项C")
    private String optionc;

    @ExcelProperty("选项D")
    private String optiond;

    @ExcelProperty("答案")
    private String answer;

    @ExcelProperty("题目类型")
    private Integer types;

    @ExcelProperty("备注")
    private String remark;




}
