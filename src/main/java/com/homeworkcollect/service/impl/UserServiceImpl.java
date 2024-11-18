package com.homeworkcollect.service.impl;

import com.homeworkcollect.dao.UserMapper;
import com.homeworkcollect.entity.User;
import com.homeworkcollect.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-14 20:52
 * @description: TODO
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkLogin(String username, String password) {
        List<User> userList = userMapper.selectUserByUsernameAndPassword(username, password);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }
}
