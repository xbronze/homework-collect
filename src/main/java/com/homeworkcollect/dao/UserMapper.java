package com.homeworkcollect.dao;

import com.homeworkcollect.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-14 20:07
 * @description: TODO
 */
public interface UserMapper {

    List<User> selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
