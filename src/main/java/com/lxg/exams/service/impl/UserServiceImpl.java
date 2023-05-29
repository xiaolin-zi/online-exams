package com.lxg.exams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxg.exams.bean.User;
import com.lxg.exams.service.UserService;
import com.lxg.exams.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xiaolin
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-03-18 14:11:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Autowired
    UserMapper userMapper;
    @Override
    public User getUser(String username, String password) {
        User user = userMapper.getUser(username, password);
        return user;
    }

    @Override
    public int addUser(String username, String password) {
        int i = userMapper.addUser(username, password);
        return i;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }
}




