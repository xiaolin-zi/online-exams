package com.lxg.exams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxg.exams.domain.User;
import com.lxg.exams.service.UserService;
import com.lxg.exams.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xiaolin
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-03-16 19:34:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String username, String password) {
        User user = userMapper.getUser(username, password);
        return user;
    }
}




