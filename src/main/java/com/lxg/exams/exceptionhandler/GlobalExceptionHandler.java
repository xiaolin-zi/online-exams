package com.lxg.exams.exceptionhandler;

import com.lxg.exams.bean.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther xiaolin
 * @creatr 2023/5/9 9:39
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public CommonResult error(Exception e){
        e.printStackTrace();
        return new CommonResult(null,"执行了全局异常处理",0,0);
    }


    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public CommonResult error(ArithmeticException e){
        e.printStackTrace();
        return new CommonResult(null,"执行了ArithmeticException异常处理",0,0);
    }


    //自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody //为了返回数据
    public CommonResult error(MyException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return new CommonResult(null,"执行了GuliException异常处理",0,0);
    }
}
