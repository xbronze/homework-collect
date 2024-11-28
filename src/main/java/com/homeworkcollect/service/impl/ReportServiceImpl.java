package com.homeworkcollect.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.homeworkcollect.custexception.NotLoginException;
import com.homeworkcollect.dao.ReportAttachmentMapper;
import com.homeworkcollect.dao.ReportMapper;
import com.homeworkcollect.entity.Report;
import com.homeworkcollect.entity.ReportAttachment;
import com.homeworkcollect.entity.User;
import com.homeworkcollect.entity.dto.ReportDTO;
import com.homeworkcollect.entity.vo.ReportVO;
import com.homeworkcollect.service.IReportService;
import com.mysql.cj.conf.StringProperty;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.management.monitor.StringMonitor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    public List<ReportVO> getReportListByUserId(Integer userId) {
        return reportMapper.selectReportAndAttachmentByUserId(userId);
    }

    /**
     * 新增实验报告
     * @param reportDTO 实验报告内容
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public ModelAndView addReport(ReportDTO reportDTO, Integer userId) {

        // 获取当前时间
        Date currentDate = new Date();
        Report report = new Report();
        BeanUtils.copyProperties(reportDTO, report);
        report.setUserId(userId);
        // 时间戳 + [10, 100)的随机数
        report.setReportCode(DateUtil.format(currentDate, "yyyyMMddHHmmss") + RandomUtil.randomInt(10, 100));
        report.setCreateTime(currentDate);
        report.setUpdateTime(currentDate);
        int reseult = reportMapper.insertReport(report);
        if (reseult < 1) {
            throw new RuntimeException("实验报告内容新增失败");
        }
        if (reportDTO.getAttachments() != null && reportDTO.getAttachments().size() > 0) {
            List<ReportAttachment> reportAttachmentList = new ArrayList<ReportAttachment>();
            for (String attachmentPath : reportDTO.getAttachments()) {
                ReportAttachment reportAttachment = new ReportAttachment();
                reportAttachment.setReportId(report.getId());
                reportAttachment.setAddress(attachmentPath);
                reportAttachment.setCreateTime(currentDate);
                reportAttachment.setUpdateTime(currentDate);
                reportAttachmentList.add(reportAttachment);
            }
            int result2 = reportAttachmentMapper.insertReportAttachment(reportAttachmentList);
            if (result2 < 0) {
                throw new RuntimeException("实验报告附件保存失败");
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        List<ReportVO> reportList = getReportListByUserId(userId);
        modelAndView.addObject("reportList", reportList);
        modelAndView.setViewName("index");
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
        Date currentDate = new Date();
        Report report = new Report();
        BeanUtils.copyProperties(reportDTO, report);
        int result = reportMapper.updateReport(report);
        if (result < 1) {
            throw new RuntimeException("实验报告内容更新失败");
        }
        int result2 = reportAttachmentMapper.deleteReportAttachmentByReportId(reportDTO.getId());
        if (result2 < 0) {
            throw new RuntimeException("实验报告附件更新失败");
        }
        List<String> attachmentList = reportDTO.getAttachments();
        if (attachmentList != null && attachmentList.size() > 0) {
            // 要判断，那些附件是新增的，那些附件没有被修改
            List<ReportAttachment> oldAttachmentList = reportAttachmentMapper.selectReportAttachmentByReportId(reportDTO.getId());
            if (oldAttachmentList == null || oldAttachmentList.isEmpty()) {
                // 如果从数据库中没有查到数据，那么本次客户端提交过来的附件列表，都按新增执行
                List<ReportAttachment> reportAttachmentList = new ArrayList<ReportAttachment>();
                for (String attachmentPath : reportDTO.getAttachments()) {
                    ReportAttachment reportAttachment = new ReportAttachment();
                    reportAttachment.setReportId(report.getId());
                    reportAttachment.setAddress(attachmentPath);
                    reportAttachment.setCreateTime(currentDate);
                    reportAttachment.setUpdateTime(currentDate);
                    reportAttachmentList.add(reportAttachment);
                }
                int attachmentResult = reportAttachmentMapper.insertReportAttachment(reportAttachmentList);
                if (attachmentResult < 0) {
                    throw new RuntimeException("实验报告附件保存失败");
                }
            } else {
                List<String> oldAttachmentPathList = oldAttachmentList.stream()
                        .map(reportAttachment -> reportAttachment.getAddress()).collect(Collectors.toList());
                List<String>[] checkResult = checkAddOrDelete(oldAttachmentPathList, attachmentList);
                if (checkResult[0].size() > 0) {
                    // 新增
                    List<ReportAttachment> reportAttachmentList = new ArrayList<ReportAttachment>();
                    for (String attachmentPath : checkResult[0]) {
                        ReportAttachment reportAttachment = new ReportAttachment();
                        reportAttachment.setReportId(report.getId());
                        reportAttachment.setAddress(attachmentPath);
                        reportAttachment.setCreateTime(currentDate);
                        reportAttachment.setUpdateTime(currentDate);
                        reportAttachmentList.add(reportAttachment);
                    }
                    int attachmentResult = reportAttachmentMapper.insertReportAttachment(reportAttachmentList);
                    if (attachmentResult < 0) {
                        throw new RuntimeException("实验报告附件保存失败");
                    }
                }
                if (checkResult[1].size() > 0) {
                    // 删除

                }
            }



        } else {
            // 客户端传过来的附件为空，那么不管之前有没有附件，都执行删除操作
            // 不校验返回结果
            reportAttachmentMapper.deleteReportAttachmentByReportId(reportDTO.getId());
        }
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    private List<String>[] checkAddOrDelete(List<String> oldAttachmentPathList, List<String> attachmentList) {
        List<String> addList = new ArrayList<>();
        List<String> delList = new ArrayList<>();
        for (String attachment : attachmentList) {
            if (!oldAttachmentPathList.contains(attachment)) {
                addList.add(attachment);
            }
        }
        for (String oldAttachmentPath : oldAttachmentPathList) {
            if (!attachmentList.contains(oldAttachmentPath)) {
                delList.add(oldAttachmentPath);
            }
        }

        return new List[]{addList, delList};
    }

    /**
     * 课程报告删除
     * @param reportId
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public int removeReport(Integer reportId) {
        int result = reportMapper.deleteReportById(reportId);
        if (result < 1) {
            throw new RuntimeException("实验报告删除失败");
        }

        // TODO 这里先把数据库的附件记录设置为删除状态，文件服务器中的附件本体，先保留，待后续优化
        int result2 = reportAttachmentMapper.deleteReportAttachmentByReportId(reportId);
        if (result2 < 0) {
            throw new RuntimeException("实验报告附件删除失败");
        }

        return 0;
    }


}
