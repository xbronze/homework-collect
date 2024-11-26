package com.homeworkcollect.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-11-21 16:53
 * @description: TODO
 */
@Data
public class ReportDTO {

    private String reportName;
    private String reportContext;
    private String deadlineTime;
    private String[] attachments;

}
