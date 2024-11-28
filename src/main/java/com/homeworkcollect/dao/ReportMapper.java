package com.homeworkcollect.dao;

import com.homeworkcollect.entity.Report;
import com.homeworkcollect.entity.vo.ReportVO;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-15 10:41
 * @description: TODO
 */
public interface ReportMapper {

    List<ReportVO> selectReportByUserId(Integer userId);

    List<ReportVO> selectReportAndAttachmentByUserId(Integer userId);

    int insertReport(Report report);

    int updateReport(Report report);

    /**
     * 课程报告删除（逻辑删除）
     * @param id
     * @return
     */
    int deleteReportById(Integer id);

}
