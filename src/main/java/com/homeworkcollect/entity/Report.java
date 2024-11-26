package com.homeworkcollect.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-11-15 10:37
 * @description: TODO
 */
@Data
public class Report {

    private Integer id;
    private Integer userId;
    private String reportCode;
    private String reportName;
    private String reportContext;
    private Date deadlineTime;
    private Date createTime;
    private Date updateTime;
    private Integer deleted;

}
