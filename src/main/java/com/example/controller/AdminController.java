package com.example.controller;

import com.example.entity.Administrator;
import com.example.service.AdministratorServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

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
     *  用户登陆验证-----账号密码   TODO:自定义返回类----返回权限信息等
     */
    @RequestMapping(value = "/login-pass", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String login(@RequestParam(defaultValue="")String username, @RequestParam(defaultValue="")String password,
                        HttpServletRequest request){
//        System.out.println("---------用户名:"+username);
//        System.out.println("---------密码:"+password);
        //将用户名用户密码组成token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            //调用loadUserByUsername   对比用户密码及其他信息
            // 在认证成功以后会使用加载的UserDetails来封装要返回的Authentication对象，加载的UserDetails对象是包含用户权限等信息的。
            // 认证成功返回的Authentication对象将会保存在当前的SecurityContext中。
            Authentication authentication = myAuthenticationManager.authenticate(token);
            //  默认情况下，在认证成功后ProviderManager将清除返回的Authentication中的凭证信息，如密码。所以如果你在无状态的应用中将
            // 返回的Authentication信息缓存起来了，那么以后你再利用缓存的信息去认证将会失败，因为它已经不存在密码这样的凭证信息了。
            // 所以在使用缓存的时候你应该考虑到这个问题。一种解决办法是设置ProviderManager的eraseCredentialsAfterAuthentication 属性
            // 为false，或者想办法在缓存时将凭证信息一起缓存。
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            Collection<? extends GrantedAuthority> authoritieslist = authentication.getAuthorities();
//            for (GrantedAuthority grantedAuthority:authoritieslist) {
//                System.out.println(grantedAuthority.getAuthority());
//            }
            HttpSession session = request.getSession();
            // 这个非常重要，否则验证后将无法登陆
            session.setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());
            return "index";
        }catch (Exception e){
            e.printStackTrace();
            return "login";
        }
    }
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Administrator> getAll(){
        return administratorServcice.getAll();
    }

    /**
     *    获取当前用户信息
     */
    public String getMessage(){
        //获取用户信息集合
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        //在程序的任何地方 都可获得当前用户的信息
        //Authentication是一个接口，用来表示用户认证信息的，在用户登录认证之前相关信息会封装为一个Authentication具体实现类的对象，
        // 在登录认证成功之后又会生成一个信息更全面，包含用户权限等信息的Authentication对象，然后把它保存在SecurityContextHolder所
        // 持有的SecurityContext中，供后续的程序进行调用，如访问权限的鉴定等。
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
