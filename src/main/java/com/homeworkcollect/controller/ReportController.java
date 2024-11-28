package com.homeworkcollect.controller;

import com.homeworkcollect.custexception.NotLoginException;
import com.homeworkcollect.entity.User;
import com.homeworkcollect.entity.dto.ReportDTO;
import com.homeworkcollect.entity.vo.ReportVO;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            modelAndView.setViewName("login");
            modelAndView.addObject("msg", "请先登录");
            return modelAndView;
        }
        modelAndView.setViewName("index");
        // 显示当前用户布置的实验报告列表
        List<ReportVO> reportList = reportService.getReportListByUserId(user.getId());
        modelAndView.addObject("reportList", reportList);
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView add(ReportDTO reportDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new NotLoginException("未登录，请先登录！");
        }

        return reportService.addReport(reportDTO, user.getId());
    }




}
