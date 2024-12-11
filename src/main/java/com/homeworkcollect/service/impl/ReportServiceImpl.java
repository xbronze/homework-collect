package com.homeworkcollect.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.homeworkcollect.dao.ReportAttachmentMapper;
import com.homeworkcollect.dao.ReportMapper;
import com.homeworkcollect.entity.Report;
import com.homeworkcollect.entity.ReportAttachment;
import com.homeworkcollect.entity.dto.ReportDTO;
import com.homeworkcollect.entity.vo.ReportVO;
import com.homeworkcollect.service.IReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: xbronze
 * @date: 2024-11-15 10:40
 * @description: TODO
 */
@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private ReportAttachmentMapper reportAttachmentMapper;

    /**
     * 实验报告列表查询
     * @param userId
     * @return
     */
    @Override
    public List<Report> getReportListByUserId(Integer userId) {
        return reportMapper.selectReportListByUserId(userId);
    }

    /**
     * 新增实验报告
     * @param reportDTO 实验报告内容
     */
    @Override
    public ModelAndView addReport(ReportDTO reportDTO) {

        // 获取当前时间
        Date currentDate = new Date();
        Report report = new Report();
        BeanUtils.copyProperties(reportDTO, report);
        // 时间戳 + [10, 100)的随机数
        report.setReportCode(DateUtil.format(currentDate, "yyyyMMddHHmmss") + RandomUtil.randomInt(10, 100));
        report.setCreateTime(currentDate);
        report.setUpdateTime(currentDate);
        int reseult = reportMapper.insertReport(report);
        if (reseult < 1) {
            throw new RuntimeException("实验报告内容新增失败");
        }
        ModelAndView modelAndView = new ModelAndView();
        List<Report> reportList = getReportListByUserId(reportDTO.getUserId());
        modelAndView.addObject("reportList", reportList);
        modelAndView.setViewName("report_list");
        return modelAndView;
    }



    /**
     * 课程报告更新
     * @param reportDTO
     * @return
     */
    @Override
    public ModelAndView modifyReport(ReportDTO reportDTO) {
        // 获取当前时间
        Report report = new Report();
        BeanUtils.copyProperties(reportDTO, report);
        int result = reportMapper.updateReport(report);
        if (result < 1) {
            throw new RuntimeException("实验报告内容新增失败");
        }
        ModelAndView modelAndView = new ModelAndView();
        List<Report> reportList = getReportListByUserId(reportDTO.getUserId());
        modelAndView.addObject("reportList", reportList);
        modelAndView.setViewName("report_list");
        return modelAndView;
    }

    /**
     * 课程报告删除
     * @param reportId
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void removeReport(Integer reportId) {
        int result = reportMapper.deleteReportById(reportId);
        if (result < 1) {
            throw new RuntimeException("实验报告删除失败");
        }

        // TODO 但是不实现附件的操作
        // TODO 这里先把数据库的附件记录设置为删除状态，文件服务器中的附件本体，先保留，待后续优化
//        int result2 = reportAttachmentMapper.deleteReportAttachmentByReportId(reportId);
//        if (result2 < 0) {
//            throw new RuntimeException("实验报告附件删除失败");
//        }
//
//        return 0;
    }

    @Override
    public Report queryReportById(Integer reportId) {
        return reportMapper.selectReportById(reportId);
    }


}
