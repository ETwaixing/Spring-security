package com.example.controller;

import com.example.service.AdministratorServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 权限 登陆 注销操作
 *
 * Created by yonglang on 2017/4/12.
 */
@Controller
public class AdminController {
    @Autowired
    private AdministratorServcice administratorServcice;
    @Autowired
    private AuthenticationManager myAuthenticationManager;

    /**
     *  用户登陆验证-----账号密码   TODO:自定义返回类
     */
    @RequestMapping(value = "/login-pass", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String login(@RequestParam(defaultValue="")String username, @RequestParam(defaultValue="")String password,
                        HttpServletRequest request){
        System.out.println("---------用户名:"+username);
        System.out.println("---------密码:"+password);
        //将用户名用户密码组成token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            //调用loadUserByUsername
            Authentication authentication = myAuthenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            // 这个非常重要，否则验证后将无法登陆
            session.setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());
            return "index";
        }catch (Exception e){
            e.printStackTrace();
            return "login";
        }
    }

    /**
     *    获取当前用户信息
     */
    public String getMessage(){
        //获取用户信息集合
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String username="";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
