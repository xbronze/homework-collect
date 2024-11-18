package com.homeworkcollect.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-11-14 19:48
 * @description: TODO
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String code;
    private String classname;
    private String username;
    private String password;
    private String role;
    private Integer initPassword;
    private Date createTime;
    private Date updateTime;
    private Integer deleted;

}
