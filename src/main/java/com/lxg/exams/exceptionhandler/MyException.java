package com.lxg.exams.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther xiaolin
 * @creatr 2023/5/9 10:50
 */

@Data
@AllArgsConstructor // 有参构造
@NoArgsConstructor // 无参构造
public class MyException extends RuntimeException{

    private Integer code;//状态码

    private String msg;//异常信息

    @Override
    public String toString(){
        return "GuliException{" +
                "message='" + this.getMessage() +
                "', code=" + code +
                '}';
    }
}
