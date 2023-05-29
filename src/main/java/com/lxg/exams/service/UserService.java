package com.lxg.exams.service;

import com.lxg.exams.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xiaolin
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-03-18 14:11:49
*/
public interface UserService extends IService<User> {

    User getUser(String username, String password);

    int addUser(String username,String password);

    User getUserByUsername(String username);
}
