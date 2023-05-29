package com.lxg.exams.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @auther xiaolin
 * @creatr 2023/5/15 8:35
 */
//与excel表格对应的实体类
@Data
public class QuestionData {


    @ExcelProperty(index = 0)
    private String title;

    @ExcelProperty(index = 1)
    private String optiona;

    @ExcelProperty(index = 2)
    private String optionb;

    @ExcelProperty(index = 3)
    private String optionc;

    @ExcelProperty(index = 4)
    private String optiond;

    @ExcelProperty(index = 5)
    private String answer;

    @ExcelProperty(index = 6)
    private Integer type;

    @ExcelProperty(index = 7)
    private String remark;


}
