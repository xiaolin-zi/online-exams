package com.lxg.exams.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xiaolin
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-03-16 19:34:47
*/
public interface UserService extends IService<User> {

    public User getUser(String username,String password);
}
