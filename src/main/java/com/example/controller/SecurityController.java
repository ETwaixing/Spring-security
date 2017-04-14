package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * security控制类
 *
 * Created by yonglang on 2017/4/14.
 */
@Controller
public class SecurityController {

    /**
     * 拒绝访问时跳转页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/security/deny")
    public String deny(HttpServletRequest request, HttpServletResponse response){
        return "security_deny";
    }
}
