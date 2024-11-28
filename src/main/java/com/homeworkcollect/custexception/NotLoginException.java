package com.homeworkcollect.custexception;

/**
 * @author: xbronze
 * @date: 2024-11-27 11:28
 * @description: 自定义异常（用户未登录）
 */
public class NotLoginException extends RuntimeException {

    private String message;

    public NotLoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
