package com.homeworkcollect.interceptor;

import com.homeworkcollect.custexception.NotLoginException;
import com.homeworkcollect.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: xbronze
 * @date: 2024-11-27 15:15
 * @description: TODO
 */
public class CheckRoleHandlerInterceptor implements HandlerInterceptor {

    // 校验用户是否登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new NotLoginException("未登录，请先登录！");
        }
        return true;
    }
}
