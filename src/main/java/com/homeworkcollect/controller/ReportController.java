package com.homeworkcollect.controller;

import com.homeworkcollect.entity.dto.ReportDTO;
import com.homeworkcollect.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void add(ReportDTO reportDTO) {
        System.out.println(reportDTO);
    }




}
