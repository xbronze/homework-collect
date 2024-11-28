package com.homeworkcollect.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.homeworkcollect.entity.ReportAttachment;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-15 16:42
 * @description: TODO
 */
@Data
public class ReportVO implements Serializable {

    private Integer id;
    private Integer userId;
    private String reportCode;
    private String reportName;
    private String reportContext;
    private Date deadlineTime;
    private Date createTime;
    private Date updateTime;
    private List<ReportAttachment> attachmentList;

    public String formateDate(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public String formateTime(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);

    }

}
