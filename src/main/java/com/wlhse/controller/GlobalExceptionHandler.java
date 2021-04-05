package com.wlhse.controller;

import com.wlhse.exception.WLHSException;
import com.wlhse.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handle(Exception e, HttpServletRequest request) {
        logger.error("URL:" + request.getServletPath() + "   METHOD:" + request.getMethod() + "   MESSAGE:" + e.getMessage(), e);
        return R.error();
    }

    @ExceptionHandler(WLHSException.class)
    @ResponseBody
    public R handle1(WLHSException e, HttpServletRequest request) {
        logger.error("URL:" + request.getServletPath() + "   METHOD:" + request.getMethod() +
                "   MESSAGE:" + e.getMessage(), e);
        R r = new R();
        r.put("code", e.getCode());
        r.put("message", e.getMessage());
        return r;
    }
}