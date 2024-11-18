package com.homeworkcollect.service;

import com.homeworkcollect.entity.Report;
import com.homeworkcollect.entity.vo.ReportVO;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-15 10:39
 * @description: TODO
 */
public interface IReportService {

    public List<ReportVO> getReportListByUserId(Integer userId);
}
