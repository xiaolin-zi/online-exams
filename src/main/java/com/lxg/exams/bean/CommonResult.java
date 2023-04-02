package com.lxg.exams.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @auther xiaolin
 * @creatr 2023/4/2 0:57
 */



//自己封装逛一逛页面数据实体类，供前端使用的数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //返回数据
    List<T> data;
    //返回信息
    String msg;
    //返回状态码
    int code;
    //返回总数
    int count;

}
