package com.homeworkcollect.controller;

import com.homeworkcollect.custexception.NotLoginException;
import com.homeworkcollect.entity.Report;
import com.homeworkcollect.entity.User;
import com.homeworkcollect.entity.dto.ReportDTO;
import com.homeworkcollect.entity.vo.ReportAttachmentVO;
import com.homeworkcollect.service.IReportAttachmentService;
import com.homeworkcollect.service.IReportService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-14 18:41
 * @description: TODO
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IReportAttachmentService reportAttachmentService;

    @RequestMapping("/reportAddModify")
    public String report_add_modify() {
        return "report_add_modify";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report_list");
        // 显示当前用户布置的实验报告列表
        User user = (User) request.getSession().getAttribute("user");
        List<Report> reportList = reportService.getReportListByUserId(user.getId());
        modelAndView.addObject("reportList", reportList);
        return modelAndView;
    }


    @RequestMapping(value = "/detail/{type}/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("type") String type, @PathVariable("id") Integer reportId,
                               HttpServletRequest request) {
        if (reportId == null) {
            throw new RuntimeException("reportId is null");
        }
        ModelAndView modelAndView = new ModelAndView();
        if ("modify".equals(type)) {
            Report report = reportService.queryReportById(reportId);
            modelAndView.addObject("report", report);
            modelAndView.setViewName("report_add_modify");
        }
        if ("context".equals(type)) {
            Report report = reportService.queryReportById(reportId);
            modelAndView.addObject("report", report);
            List<ReportAttachmentVO> reportAttachmentVOList = reportAttachmentService.queryReportAttachmentVOList(reportId);
            modelAndView.addObject("reportAttachmentVOList", reportAttachmentVOList);
            modelAndView.setViewName("report_detail");
        }
        return modelAndView;
    }

    /**
     * 提交实验报告内容，用于新增或更新
     * @param reportDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "/submitContext", method = RequestMethod.POST)
    public ModelAndView submitContext(ReportDTO reportDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new NotLoginException("未登录，请先登录！");
        }
        reportDTO.setUserId(user.getId());
        if (reportDTO.getReportCode() != null && !"".equals(reportDTO.getReportCode())) {
            // 更新
            return reportService.modifyReport(reportDTO);
        } else {
            // 新增
            return reportService.addReport(reportDTO);
        }
    }


}
