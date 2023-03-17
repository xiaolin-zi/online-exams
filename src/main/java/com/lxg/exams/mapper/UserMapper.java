package com.lxg.exams.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author xiaolin
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-03-16 19:34:47
* @Entity com.lxg.exams.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from t_user where username=#{username} and password=#{password}")
    public User getUser(String username,String password);
}




