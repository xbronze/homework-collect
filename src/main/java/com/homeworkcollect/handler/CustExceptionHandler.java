package com.homeworkcollect.handler;

import com.homeworkcollect.custexception.NotLoginException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: xbronze
 * @date: 2024-11-27 11:33
 * @description: TODO
 */
@ControllerAdvice
public class CustExceptionHandler {

    // 针对用户未登录的异常处理
    @ExceptionHandler(NotLoginException.class)
    public ModelAndView handleException1(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exp", exception);
        mav.setViewName("customExp");
        return mav;
    }

    // 其他异常处理
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException2(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exp", exception);
        mav.setViewName("defaultExp");
        return mav;
    }


}
