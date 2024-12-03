package com.homeworkcollect.service;

import com.homeworkcollect.entity.Report;
import com.homeworkcollect.entity.dto.ReportDTO;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-15 10:39
 * @description: TODO
 */
public interface IReportService {

    public List<Report> getReportListByUserId(Integer userId);

    /**
     * 新增实验报告
     * @param reportDTO 实验报告内容
     */
    public ModelAndView addReport(ReportDTO reportDTO);

    public ModelAndView modifyReport(ReportDTO reportDTO);

    public int removeReport(Integer reportId);

    public Report queryReportById(Integer reportId);
}
