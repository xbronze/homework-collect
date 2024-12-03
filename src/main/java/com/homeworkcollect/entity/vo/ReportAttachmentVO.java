package com.homeworkcollect.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-12-03 16:12
 * @description: TODO
 */
@Data
public class ReportAttachmentVO {

    private Integer id;
    private Integer reportId;
    private String fileName;
    private String address;
    private String downloadUrl;
    private Date createTime;
    private Date updateTime;

}
