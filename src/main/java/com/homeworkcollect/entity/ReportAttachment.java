package com.homeworkcollect.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-11-27 10:58
 * @description: TODO
 */
@Data
public class ReportAttachment {

    private Integer id;
    private Integer reportId;
    private String address;
    private Date createTime;
    private Date updateTime;

}
