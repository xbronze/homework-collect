package com.homeworkcollect.service;

import com.homeworkcollect.entity.User;

/**
 * @author: xbronze
 * @date: 2024-11-14 20:52
 * @description: TODO
 */
public interface IUserService {

    public User checkLogin(String username, String password);
}
