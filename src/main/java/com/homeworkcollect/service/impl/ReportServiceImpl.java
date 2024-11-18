package com.homeworkcollect.service.impl;

import com.homeworkcollect.dao.ReportMapper;
import com.homeworkcollect.entity.Report;
import com.homeworkcollect.entity.vo.ReportVO;
import com.homeworkcollect.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-15 10:40
 * @description: TODO
 */
@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<ReportVO> getReportListByUserId(Integer userId) {
        return reportMapper.selectReportByUserId(userId);
    }
}
