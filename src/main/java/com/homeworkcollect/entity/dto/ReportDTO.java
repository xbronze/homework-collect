package com.homeworkcollect.entity.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-21 16:53
 * @description: TODO
 */
@Data
public class ReportDTO {

    private Integer id;
    private String reportName;
    private String reportContext;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadlineTime;

    private List<String> attachments;

}
