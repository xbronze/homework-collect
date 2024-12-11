package com.homeworkcollect.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
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
    private String reportIntroduction;
    private String reportContext;
    private Date deadlineTime;
    private Date createTime;
    private Date updateTime;
    private Integer deleted;

    public String formateDate(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public String formateTime(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);

    }
}
