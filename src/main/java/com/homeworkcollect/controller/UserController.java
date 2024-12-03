package com.homeworkcollect.controller;

import com.homeworkcollect.entity.User;
import com.homeworkcollect.entity.vo.ReportVO;
import com.homeworkcollect.service.IReportService;
import com.homeworkcollect.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-13 10:34
 * @description: TODO
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IReportService reportService;

    @RequestMapping("/loginCheck")
    public String loginCheck(@RequestParam("username") String username, @RequestParam("password") String password,
                             HttpServletRequest request, Model model) {
        User user = userService.checkLogin(username, password);
        if (user == null) {
            model.addAttribute("msg", "输入的账号密码错误，请重新输入");
            return "login";
        }
        // 登录成功，session保存当前登录用户的信息
        request.getSession().setAttribute("user", user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user) {
        System.out.println(user);
        return "login";
    }


}
