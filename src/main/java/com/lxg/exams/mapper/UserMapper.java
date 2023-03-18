package com.lxg.exams.mapper;

import com.lxg.exams.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author xiaolin
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-03-18 14:11:49
* @Entity com.lxg.exams.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from t_user where username = #{username} and password = #{password}")
    public User getUser(String username,String password);


    @Insert("insert into t_user (username,password) values (#{username},#{password})")
    public int addUser(String username,String password);
}




