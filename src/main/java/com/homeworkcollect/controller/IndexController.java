package com.homeworkcollect.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: xbronze
 * @date: 2024-11-27 15:09
 * @description: TODO
 */
@Controller
public class IndexController {

    /**
     * 首页重定向到 实验报告列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "redirect:/report/list";
    }

}
